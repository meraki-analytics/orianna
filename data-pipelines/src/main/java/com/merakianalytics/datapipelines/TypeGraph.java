package com.merakianalytics.datapipelines;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.datapipelines.transformers.DataTransformer;

import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.problem.SearchProblem;
import es.usc.citius.hipster.util.Function;

/**
 * It's just like... why would you call a library Hipster4j?
 */
@SuppressWarnings("rawtypes") // Class<?> doesn't play well with well-specified class types on inference
public class TypeGraph {
    private final Map<Class<?>, Map<Class<?>, DataTransformer>> cheapest;
    private final HipsterDirectedGraph<Class, DataTransformer> graph;

    public TypeGraph(final Collection<? extends DataTransformer> transformers) {
        // Get cheapest transform for each type transition
        final Map<Class<?>, Map<Class<?>, DataTransformer>> cheapestTransformer = new HashMap<>();
        for(final DataTransformer transformer : transformers) {
            final Map<Class<?>, Set<Class<?>>> transforms = transformer.transforms();
            for(final Class<?> from : transforms.keySet()) {
                for(final Class<?> to : transforms.get(from)) {
                    if(transformer.cost() < cheapestTransformer.get(from).get(to).cost()) {
                        cheapestTransformer.get(from).put(to, transformer);
                    }
                }
            }
        }
        for(final Class<?> from : cheapestTransformer.keySet()) {
            cheapestTransformer.put(from, Collections.unmodifiableMap(cheapestTransformer.get(from)));
        }
        cheapest = Collections.unmodifiableMap(cheapestTransformer);

        // Build graph
        final GraphBuilder<Class, DataTransformer> builder = GraphBuilder.<Class, DataTransformer> create();
        for(final Class<?> from : cheapest.keySet()) {
            for(final Class<?> to : cheapest.get(from).keySet()) {
                final DataTransformer transformer = cheapest.get(from).get(to);
                builder.connect(from).to(to).withEdge(transformer);
            }
        }

        graph = builder.createDirectedGraph();
    }

    @SuppressWarnings("unchecked") // Generics are not the strong suit of Hipster4j.
    public ChainTransform getTransform(final Class from, final Class<?> to) {
        if(from.equals(to)) {
            return ChainTransform.identity(to);
        }

        final SearchProblem problem = GraphSearchProblem.startingFrom(from).in(graph).extractCostFromEdges(new Function<DataTransformer, Double>() {
            @Override
            public Double apply(final DataTransformer transformer) {
                return new Double(transformer.cost());
            }
        }).build();

        final List<Class<?>> path = (List<Class<?>>)Hipster.createDijkstra(problem).search(to).getOptimalPaths().get(0);
        final List<DataTransformer> transform = new LinkedList<>();
        for(int i = 1; i < path.size(); i++) {
            transform.add(cheapest.get(path.get(i - 1)).get(path.get(i)));
        }
        return new ChainTransform(from, to, path, transform);
    }
}
