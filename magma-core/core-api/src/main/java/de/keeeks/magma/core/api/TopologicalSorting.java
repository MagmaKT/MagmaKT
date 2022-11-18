package de.keeeks.magma.core.api;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

@RequiredArgsConstructor
public final class TopologicalSorting {
    private final Graph<ModuleContainer, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);
    private final List<ModuleContainer> modules = new LinkedList<>();

    private final Logger logger;

    public void add(ModuleDescription description, Class<? extends Module> mainClass) {
        modules.add(ModuleContainer.create(description, mainClass));
    }

    public List<ModuleContainer> sorted() {
        modules.forEach(container -> {
            graph.addVertex(container);
            AtomicBoolean success = new AtomicBoolean(true);

            for (String dependencyName : container.description().depends()) {
                getModuleByName(dependencyName).ifPresentOrElse(
                        dependency -> success(container, dependency),
                        failed(container, dependencyName, success)
                );
            }

            for (String dependencyName : container.description().softDepends()) {
                getModuleByName(dependencyName).ifPresent(moduleContainer -> success(container, moduleContainer));
            }

            if (success.get()) {
                container.enable();
            }
        });
        return IteratorUtils.toList(new TopologicalOrderIterator<>(graph));
    }

    private void success(ModuleContainer container, ModuleContainer dependency) {
        graph.addVertex(container);
        if (!graph.containsVertex(dependency)) {
            graph.addVertex(dependency);
        }
        graph.addEdge(dependency, container);
    }

    private Runnable failed(ModuleContainer container, String dependencyName, AtomicBoolean success) {
        return () -> {
            logger.warning("Dependency for module %s not present: %s".formatted(
                    container.name(),
                    dependencyName
            ));
            success.set(false);
        };
    }

    private Optional<ModuleContainer> getModuleByName(String name) {
        return modules.stream().filter(module -> module.name().equals(name)).findFirst();
    }
}