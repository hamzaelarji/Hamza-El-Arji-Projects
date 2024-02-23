"use strict";
import {Std, FrStdt} from "./exercise3.mjs";
import fs from 'fs';

export class Prmtn {
    constructor() {
        this.students = [];
    }

    add(student) {
        this.students.push(student);
    }

    size() {
        return this.students.length;
    }

    get(i) {
        return this.students[i];
    }

    print() {
        const printed = this.students.map(student => student.toString()).join('\n');
        console.log(printed);
        return printed;
    }

    write() {
        return JSON.stringify(this.students);
    }

    read(str) {
        const parsedData = JSON.parse(str);
        this.students = parsedData.map(studentData => {
            if (studentData.type === 'Std') {
                return new Std(studentData.lastName, studentData.firstName, studentData.id);
            } else {
                return new FrStdt(studentData.lastName, studentData.firstName, studentData.id, studentData.nationality);
            }
        });
    }

    saveTo(fileName) {
        const data = JSON.stringify(this.students);
        fs.writeFileSync(fileName, data);
    }

    readFrom(fileName) {
        try {
            // Read contents from the file
            const jsonData = fs.readFileSync(fileName, 'utf8');

            // Reconstruct Prmtn instance using read method
            this.read(jsonData);
        } catch (error) {
            console.error('Error reading file:', error);
        }
    }
}


