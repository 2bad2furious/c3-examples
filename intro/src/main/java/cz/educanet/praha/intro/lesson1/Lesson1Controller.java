package cz.educanet.praha.intro.lesson1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Lesson1Controller {

    @GetMapping("/factorial/{n}")
    public int factorial(@PathVariable int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    @GetMapping("/calculator")
    public Results calculator(@RequestParam int a, @RequestParam int b) {
        return new Results(
                a + b,
                a - b,
                a * b,
                (double) a / b
        );
    }

    @GetMapping("/prime-numbers")
    public ArrayList<Integer> primeNumbers(
            @RequestParam(required = false) Integer from,
            @RequestParam(required = false) Integer to
    ) {
        from = from == null ? 2 : from;
        to = to == null ? from + 100 : to;
        ArrayList<Integer> allPrimes = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 2; i <= to; i++) {
            if (isPrime(i, allPrimes)) {
                allPrimes.add(i);
                if (i >= from) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    private boolean isPrime(int i, List<Integer> primes) {
        int sqrt = (int) Math.floor(Math.sqrt(i));
        for (int prime : primes) {
            if (prime > sqrt) {
                break;
            }
            if (i % prime == 0) {
                return false;
            }
        }
        return true;
    }

}