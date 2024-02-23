"use strict";

// Q1
export function wordc(str) {
    const words = str.split(" ");
    const wordCount = {};
    for (let i = 0; i < words.length; i++) {
        const word = words[i];
        if (wordCount[word]) {
            wordCount[word]++;
        } else {
            wordCount[word] = 1;
        }
    }
    return wordCount;
}

// Q2
export class WrdLst {
    constructor(str) {
        this.str = str;
        this.wordCount = wordc(this.str);
    }

    getWords() {
        const wordsObject = this.wordCount;
        const uniqueWords = Object.keys(wordsObject);
        return uniqueWords.sort();
    }
  
    maxCountWord() {
        const words = this.getWords();
        let maxCount = 0;
        let maxCountWord = '';
        for (const word of words) {
            if (this.wordCount[word] > maxCount || (this.wordCount[word] === maxCount && (maxCountWord === '' || word < maxCountWord))) {
                maxCount = this.wordCount[word];
                maxCountWord = word;
            }
        }
        return maxCountWord;
    }
  
    minCountWord() {
        const words = this.getWords();
        let minCount = Infinity;
        let minCountWord = '';
        for (const word of words) {
            if (this.wordCount[word] < minCount || (this.wordCount[word] === minCount && (minCountWord === '' || word < minCountWord))) {
                minCount = this.wordCount[word];
                minCountWord = word;
            }
        }
        return minCountWord;
    }

    getCount(word) {
        const stringWord = word.toString();
        return this.wordCount[stringWord] || 0;
    }
    applyWordFunc(f) {
        const words = this.getWords(); 
        const results = [];
        for (const word of words) {
            const result = f(word);
            results.push(result);
        }

        return results; 
    }
}




