"use strict";

// iterative
export function fiboIt(n) {
    let fib = [];
    fib[0] = 0;
    fib[1] = 1;
    for (let i = 2; i <= n; i++) {
        fib[i] = fib[i - 1] + fib[i - 2];
    }
    return fib[n];
}

// recursive function
export function fibo_rec(n) {
    if (n < 2) {
        return n;
    }
    return fibo_rec(n - 1) + fibo_rec(n - 2);
}

// process array, no map
export function fibonaArr(t) {
    let fib = [];
    let longueur = t.length;
    for (let i = 0; i < longueur; i++) {
        fib.push(fibo_rec(t[i]));
    }
    return fib;
}

// with map
export function fibonaMap(t) {
    return t.map(fibo_rec); // applies function fibo_rec + returns the array of results
}