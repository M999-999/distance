package com.training.distance.utils;

import com.training.distance.domain.City;
import com.training.distance.domain.Distance;
import com.training.distance.domain.Graph;

import java.util.*;

public class DijkstraAlgorithm1 {

        private final List<City> nodes;
        private final List<Distance> distances;
        private Set<City> settledNodes;
        private Set<City> unSettledNodes;
        private Map<City, City> predecessors;
        private Map<City, Integer> distance;

        public DijkstraAlgorithm1(Graph graph) {
            // create a copy of the array so that we can operate on this array
            this.nodes = new ArrayList<City>(graph.getCities());
            this.distances = new ArrayList<Distance>(graph.getDistances());
        }

        public void execute(City source) {
            settledNodes = new HashSet<City>();
            unSettledNodes = new HashSet<City>();
            distance = new HashMap<City, Integer>();
            predecessors = new HashMap<City, City>();
            distance.put(source, 0);
            unSettledNodes.add(source);
            while (unSettledNodes.size() > 0) {
                City node = getMinimum(unSettledNodes);
                settledNodes.add(node);
                unSettledNodes.remove(node);
                findMinimalDistances(node);
            }
        }

        private void findMinimalDistances(City node) {
            List<City> adjacentNodes = getNeighbors(node);
            for (City target : adjacentNodes) {
                if (getShortestDistance(target) > getShortestDistance(node)
                        + getDistance(node, target)) {
                    distance.put(target, getShortestDistance(node)
                            + getDistance(node, target));
                    predecessors.put(target, node);
                    unSettledNodes.add(target);
                }
            }

        }

        private int getDistance(City node, City target) {
            for (Distance distance : distances) {
                if (distance.getSourceCity().equals(node)
                        && distance.getTargetCity().equals(target)) {
                    return distance.getDistance();
                }
            }
            throw new RuntimeException("Should not happen");
        }

        private List<City> getNeighbors(City node) {
            List<City> neighbors = new ArrayList<City>();
            for (Distance distance : distances) {
                if (distance.getSourceCity().equals(node)
                        && !isSettled(distance.getTargetCity())) {
                    neighbors.add(distance.getTargetCity());
                }
            }
            return neighbors;
        }

        private City getMinimum(Set<City> cities) {
            City minimum = null;
            for (City city : cities) {
                if (minimum == null) {
                    minimum = city;
                } else {
                    if (getShortestDistance(city) < getShortestDistance(minimum)) {
                        minimum = city;
                    }
                }
            }
            return minimum;
        }

        private boolean isSettled(City city) {
            return settledNodes.contains(city);
        }

        private int getShortestDistance(City destination) {
            Integer d = distance.get(destination);
            if (d == null) {
                return Integer.MAX_VALUE;
            } else {
                return d;
            }
        }

        /*
         * This method returns the path from the source to the selected target and
         * NULL if no path exists
         */
        public LinkedList<City> getPath(City target) {
            LinkedList<City> path = new LinkedList<City>();
            City step = target;
            // check if a path exists
            if (predecessors.get(step) == null) {
                return null;
            }
            path.add(step);
            while (predecessors.get(step) != null) {
                step = predecessors.get(step);
                path.add(step);
            }
            // Put it into the correct order
            Collections.reverse(path);
            return path;
        }

    }
