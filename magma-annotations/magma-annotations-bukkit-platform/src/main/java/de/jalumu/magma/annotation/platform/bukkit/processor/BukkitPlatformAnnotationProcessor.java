package de.jalumu.magma.annotation.platform.bukkit.processor;

import de.jalumu.magma.annotation.bukkit.platform.application.BukkitApiVersion;
import de.jalumu.magma.annotation.bukkit.platform.application.BukkitPlugin;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileManager.Location;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.nodes.Tag;

@SupportedAnnotationTypes({"de.jalumu.magma.annotation.bukkit.platform.*"})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public final class AnnotationProcessor extends AbstractProcessor {
    public boolean process(@Nullable Set annotations, @Nullable RoundEnvironment roundEnv) {
        Element mainPluginElement = null;
        Set var10000 = roundEnv != null ? roundEnv.getElementsAnnotatedWith(BukkitPlugin.class) : null;
        if (var10000 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Set<javax.lang.model.element.Element>");
        } else {
            Set elements = var10000;
            if (elements.size() > 1) {
                this.raiseError("Found more than one plugin main class");
                return false;
            } else if (elements.isEmpty()) {
                return false;
            } else {
                mainPluginElement = (Element) elements.iterator().next();
                if (mainPluginElement instanceof TypeElement) {
                    TypeElement mainPluginType = (TypeElement) mainPluginElement;
                    if (!(mainPluginType.getEnclosingElement() instanceof PackageElement)) {
                        this.raiseError("Main plugin class is not a top-level class", (Element) mainPluginType);
                        return false;
                    } else if (mainPluginType.getModifiers().contains(Modifier.STATIC)) {
                        this.raiseError("Main plugin class cannot be static nested", (Element) mainPluginType);
                        return false;
                    } else {
                        ProcessingEnvironment var26 = this.processingEnv;
                        if (!var26.getTypeUtils().isSubtype(mainPluginType.asType(), this.fromClass(JavaPlugin.class))) {
                            this.raiseError("Main plugin class is not an subclass of JavaPlugin!", (Element) mainPluginType);
                        }

                        if (mainPluginType.getModifiers().contains(Modifier.ABSTRACT)) {
                            this.raiseError("Main plugin class cannot be abstract", (Element) mainPluginType);
                            return false;
                        } else {
                            this.checkForNoArgsConstructor(mainPluginType);
                            Map yml = (Map) (new LinkedHashMap());
                            Map appYaml = (Map) (new LinkedHashMap());
                            String mainName = mainPluginType.getQualifiedName().toString();
                            yml.put("main", mainName);
                            Element var10003 = (Element) mainPluginType;
                            //int var11 = StringsKt.lastIndexOf$default((CharSequence) mainName, '.', 0, false, 6, (Object) null) + 1;
                            String var12 = mainName.substring(mainName.lastIndexOf('.'));
                            try {
                                String name = (String) this.processAndPut(yml, "name", var10003, var12, BukkitPlugin.class, String.class, "name");
                                this.processAndPutString(yml, "version", (Element) mainPluginType, "", BukkitPlugin.class, "version");
                                this.processAndPutString(yml, "description", (Element) mainPluginType, "", BukkitPlugin.class, "description");
                                var10003 = (Element) mainPluginType;
                                this.processAndPutString(yml, "prefix", var10003, name, BukkitPlugin.class, "prefix");
                                yml.put("api-version", ((BukkitApiVersion) this.process((Element) mainPluginType, BukkitApiVersion.V1_16, BukkitPlugin.class, BukkitApiVersion.class, "apiVersion")).getValue());
                                this.processAndPutString(yml, "author", (Element) mainPluginType, "", BukkitPlugin.class, "author");
                                this.processAndPutArray(yml, "authors", (Element) mainPluginType, new String[0], BukkitPlugin.class, "authors");
                                this.processAndPutArray(yml, "softdepend", (Element) mainPluginType, new String[0], BukkitPlugin.class, "softDependsPlugin");
                                this.processAndPutArray(yml, "depend", (Element) mainPluginType, new String[0], BukkitPlugin.class, "dependsPlugin");
                                this.processAndPutArray(yml, "loadbefore", (Element) mainPluginType, new String[0], BukkitPlugin.class, "loadBeforePlugin");
                            } catch (Exception e) {
                                e.printStackTrace();
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }

                            try {
                                Yaml yaml = new Yaml();
                                var26 = this.processingEnv;
                                FileObject file = var26.getFiler().createResource((Location) StandardLocation.CLASS_OUTPUT, (CharSequence) "", (CharSequence) "plugin.yml", new Element[0]);
                                Closeable var24 = (Closeable) file.openWriter();
                                Throwable var13 = null;

                                try {
                                    Writer w = (Writer) var24;
                                    w.append((CharSequence) "# plugin.yml automatically generated from Fusion-Annotations").append((CharSequence) "\n\n");
                                    String raw = yaml.dumpAs(yml, Tag.MAP, FlowStyle.BLOCK);
                                    w.write(raw);
                                    w.flush();
                                    w.close();
                                } catch (Throwable var20) {
                                    var13 = var20;
                                    throw var20;
                                } finally {

                                }

                                return true;
                            } catch (IOException var22) {
                                try {
                                    throw (Throwable) (new RuntimeException((Throwable) var22));
                                } catch (Throwable e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } else {
                    this.raiseError("Main plugin class is not a class", mainPluginElement);
                    return false;
                }
            }
        }
        return false;
    }

    private final Object processAndPut(Map map, String name, Element el, Object defaultVal, Class annotationType, Class returnType, String methodName) throws Throwable {
        Object result = this.process(el, defaultVal, annotationType, returnType, methodName);
        if (result != null) {
            map.put(name, result);
        }

        return result;
    }

    private final String processAndPutString(Map map, String name, Element el, String defaultVal, Class annotationType, String methodName) throws Throwable {
        String result = this.processToString(el, defaultVal, annotationType, methodName);
        map.put(name, result);

        return result;
    }

    private final void processAndPutArray(Map map, String name, Element el, Object[] defaultVal, Class annotationType, String methodName) throws Throwable {
        Object[] result = this.processToArray(el, defaultVal, annotationType, methodName);
        map.put(name, result);
    }

    private final Object process(Element el, Object defaultVal, Class annotationType, Class returnType, String methodName) throws Throwable {
        Object result = null;
        Annotation ann = el.getAnnotation(annotationType);
        Object var10000;
        if (ann == null) {
            var10000 = defaultVal;
        } else {
            Object var11;
            try {
                Method value = annotationType.getMethod(methodName);
                Object res = value.invoke(ann);
                var11 = returnType.cast(res);
            } catch (Exception var10) {
                throw (Throwable) (new RuntimeException((Throwable) var10));
            }

            var10000 = var11;
        }

        result = var10000;
        return result;
    }

    private final String processToString(Element el, String defaultVal, Class annotationType, String methodName) throws Throwable {
        String result = null;
        Annotation ann = el.getAnnotation(annotationType);
        if (ann == null) {
            return defaultVal;
        } else {
            try {
                Method value = annotationType.getMethod(methodName);
                Object res = value.invoke(ann);
                return res.toString();
            } catch (Exception var9) {
                throw (Throwable) (new RuntimeException((Throwable) var9));
            }
        }
    }

    private final Object[] processToArray(Element el, Object[] defaultVal, Class annotationType, String methodName) throws Throwable {
        Object[] result = null;
        Annotation ann = el.getAnnotation(annotationType);
        if (ann == null) {
            return defaultVal;
        } else {
            try {
                Method value = annotationType.getMethod(methodName);
                Object res = value.invoke(ann);
                if (res == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<*>");
                } else {
                    return (Object[]) res;
                }
            } catch (Exception var9) {
                throw (Throwable) (new RuntimeException((Throwable) var9));
            }
        }
    }

    private final void checkForNoArgsConstructor(TypeElement mainPluginType) {
        Collection var2 = (Collection) mainPluginType.getEnclosedElements();
        if (var2 != null && !var2.isEmpty()) {
            Iterator var3 = ElementFilter.constructorsIn((Iterable) mainPluginType.getEnclosedElements()).iterator();

            while (var3.hasNext()) {
                ExecutableElement constructor = (ExecutableElement) var3.next();
                if (constructor.getParameters().isEmpty()) {
                    return;
                }
            }
        }

        this.raiseError("Main plugin class must have a no argument constructor.", (Element) mainPluginType);
    }

    private TypeMirror fromClass(Class clazz) {
        ProcessingEnvironment var10000 = this.processingEnv;
        return var10000.getElementUtils().getTypeElement((CharSequence) clazz.getName()).asType();
    }

    private void raiseError(String message) {
        ProcessingEnvironment var10000 = this.processingEnv;
        var10000.getMessager().printMessage(Kind.ERROR, (CharSequence) message);
    }

    private void raiseError(String message, Element element) {
        ProcessingEnvironment var10000 = this.processingEnv;
        var10000.getMessager().printMessage(Kind.ERROR, (CharSequence) message, element);
    }
}
