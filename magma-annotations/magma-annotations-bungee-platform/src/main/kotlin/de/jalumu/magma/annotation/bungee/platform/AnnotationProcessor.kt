package de.jalumu.magma.annotation.bungee.platform

import de.jalumu.magma.annotation.bungee.platform.application.BungeecordPlugin
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.tools.Diagnostic

import javax.lang.model.util.ElementFilter

import javax.lang.model.type.TypeMirror
import java.lang.Exception
import java.io.IOException

import org.yaml.snakeyaml.DumperOptions

import javax.tools.StandardLocation

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.nodes.Tag
import net.md_5.bungee.api.plugin.Plugin
import javax.lang.model.element.*

@SupportedAnnotationTypes("de.jalumu.magma.annotation.bungee.platform.*")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
class AnnotationProcessor : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        val mainPluginElement: Element?

        val elements: Set<Element> = roundEnv?.getElementsAnnotatedWith(BungeecordPlugin::class.java) as Set<Element>

        if (elements.size > 1) {
            raiseError("Found more than one plugin main class")
            return false
        }

        if (elements.isEmpty()) {
            return false
        }

        mainPluginElement = elements.iterator().next()

        val mainPluginType: TypeElement = if (mainPluginElement is TypeElement) {
            mainPluginElement
        } else {
            raiseError("Main plugin class is not a class", mainPluginElement)
            return false
        }

        if (mainPluginType.enclosingElement !is PackageElement) {
            raiseError("Main plugin class is not a top-level class", mainPluginType)
            return false
        }

        if (mainPluginType.modifiers.contains(Modifier.STATIC)) {
            raiseError("Main plugin class cannot be static nested", mainPluginType)
            return false
        }

        if (!processingEnv.typeUtils.isSubtype(mainPluginType.asType(), fromClass(Plugin::class.java))) {
            raiseError("Main plugin class is not an subclass of JavaPlugin!", mainPluginType)
        }

        if (mainPluginType.modifiers.contains(Modifier.ABSTRACT)) {
            raiseError("Main plugin class cannot be abstract", mainPluginType)
            return false
        }

        checkForNoArgsConstructor(mainPluginType)

        val yml: MutableMap<String, Any> = linkedMapOf()

        val appYaml: MutableMap<String, Any> = linkedMapOf()

        val mainName = mainPluginType.qualifiedName.toString()

        yml["main"] = mainName

        val name = processAndPut(
            yml, "name", mainPluginType, mainName.substring(mainName.lastIndexOf('.') + 1),
            BungeecordPlugin::class.java,
            String::class.java, "name"
        )

        processAndPutString(yml, "version", mainPluginType, "", BungeecordPlugin::class.java, "version")

        processAndPutString(yml, "description", mainPluginType, "", BungeecordPlugin::class.java, "description")

        processAndPutString(yml, "prefix", mainPluginType, name!!, BungeecordPlugin::class.java, "prefix")

        processAndPutString(yml, "author", mainPluginType, "", BungeecordPlugin::class.java, "author")

        processAndPutArray(
            yml,
            "authors",
            mainPluginType,
            emptyArray<String>(),
            BungeecordPlugin::class.java,
            "authors"
        )

        processAndPutArray(
            yml,
            "softdepend",
            mainPluginType,
            emptyArray<String>(),
            BungeecordPlugin::class.java,
            "softDependsPlugin"
        )

        processAndPutArray(
            yml,
            "depend",
            mainPluginType,
            emptyArray<String>(),
            BungeecordPlugin::class.java,
            "dependsPlugin"
        )

        processAndPutArray(
            yml,
            "loadbefore",
            mainPluginType,
            emptyArray<String>(),
            BungeecordPlugin::class.java,
            "loadBeforePlugin"
        )

        try {
            val yaml = Yaml()
            val file = processingEnv.filer.createResource(StandardLocation.CLASS_OUTPUT, "", "bungee.yml")
            file.openWriter().use { w ->
                w.append("# bungee.yml automatically generated from Magma-Annotations")
                    .append("\n\n")
                val raw = yaml.dumpAs(yml, Tag.MAP, DumperOptions.FlowStyle.BLOCK)
                w.write(raw)
                w.flush()
                w.close()
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

        return true
    }

    private fun <A : Annotation?, R> processAndPut(
        map: MutableMap<String, Any>,
        name: String,
        el: Element,
        defaultVal: R,
        annotationType: Class<A>,
        returnType: Class<R>,
        methodName: String
    ): R? {
        val result: R? = process(el, defaultVal, annotationType, returnType, methodName)
        if (result != null) map[name] = result
        return result
    }

    private fun <A : Annotation?> processAndPutString(
        map: MutableMap<String, Any>,
        name: String,
        el: Element,
        defaultVal: String,
        annotationType: Class<A>,
        methodName: String
    ): String {
        val result: String = processToString(el, defaultVal, annotationType, methodName)
        if (result != "")
            map[name] = result
        return result
    }

    private fun <A : Annotation?> processAndPutArray(
        map: MutableMap<String, Any>,
        name: String,
        el: Element,
        defaultVal: Array<*>,
        annotationType: Class<A>,
        methodName: String
    ) {
        val result: Array<*> = processToArray(el, defaultVal, annotationType, methodName)
        map[name] = result
    }

    private fun <A : Annotation?, R> process(
        el: Element,
        defaultVal: R,
        annotationType: Class<A>,
        returnType: Class<R>,
        methodName: String
    ): R {
        val result: R
        val ann = el.getAnnotation(annotationType)
        result = if (ann == null) defaultVal else {
            try {
                val value = annotationType.getMethod(methodName)
                val res = value.invoke(ann)
                returnType.cast(res)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        return result
    }


    private fun <A : Annotation?> processToString(
        el: Element,
        defaultVal: String,
        annotationType: Class<A>,
        methodName: String
    ): String {
        val result: String
        val ann = el.getAnnotation(annotationType)
        result = if (ann == null) defaultVal else {
            try {
                val value = annotationType.getMethod(methodName)
                val res = value.invoke(ann)
                return res.toString()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        return result
    }

    private fun <A : Annotation?> processToArray(
        el: Element,
        defaultVal: Array<*>,
        annotationType: Class<A>,
        methodName: String
    ): Array<*> {
        val result: Array<*>
        val ann = el.getAnnotation(annotationType)
        result = if (ann == null) defaultVal else {
            try {
                val value = annotationType.getMethod(methodName)
                val res = value.invoke(ann)
                return res as Array<*>
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        return result
    }

    private fun checkForNoArgsConstructor(mainPluginType: TypeElement) {
        if (!mainPluginType.enclosedElements.isNullOrEmpty())
            for (constructor in ElementFilter.constructorsIn(mainPluginType.enclosedElements)) {
                if (constructor.parameters.isEmpty()) {
                    return
                }
            }
        raiseError("Main plugin class must have a no argument constructor.", mainPluginType)
    }

    private fun fromClass(clazz: Class<*>): TypeMirror? {
        return processingEnv.elementUtils.getTypeElement(clazz.name).asType()
    }

    private fun raiseError(message: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, message)
    }

    private fun raiseError(message: String, element: Element) {
        processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, message, element)
    }

}