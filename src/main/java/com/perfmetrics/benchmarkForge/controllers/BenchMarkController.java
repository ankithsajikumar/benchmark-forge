package com.perfmetrics.benchmarkForge.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BenchMarkController {

    /**
     * Handles GET requests to the / endpoint.
     * Returns a simple greeting string.
     */
    @GetMapping("/")
    public String sayHello() {
        return "Welcome, curious optimizer! Ready to put your CPU to the test? May your benchmarks be fast and your coffee strong.";
    }

    /**
     * Performs a simple computation with a specified number of iterations.
     * The number of iterations is provided as a path variable.
     * If no path variable is provided (i.e., /compute), it defaults to 1,000,000 iterations.
     * <p>
     * You can access this endpoint in two ways:
     * 1. /compute              (uses default 100 iterations)
     * 2. /compute/10000        (uses 10,000 iterations)
     */
    @GetMapping({"/compute", "/compute/{iterations}"})
    public String performSimpleComputation(@PathVariable(required = false) Long iterations) {
        // Use a default of 100 if no iterations are provided or if the path variable is not present
        long numIterations = (iterations != null) ? iterations : 100;

        if (numIterations < 0) {
            return "Number of iterations cannot be negative.";
        }

        long startTime = System.nanoTime();
        double result = 0;
        // Simulate some CPU-bound work
        for (long i = 0; i < numIterations; i++) {
            result += Math.sqrt(i) * Math.sin(i);
        }
        long endTime = System.nanoTime();
        long durationMillis = (endTime - startTime) / 100;

        return "Computation complete! Result: " + result + ". Iterations: " + numIterations + ". Time taken: " + durationMillis + " ms. (From " + System.getProperty("os.arch") + " architecture)";
    }
}
