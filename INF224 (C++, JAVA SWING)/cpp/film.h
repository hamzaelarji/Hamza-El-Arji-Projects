#ifndef FILM_H
#define FILM_H
#include "video.h"

/**
 * @brief The Film class
 */
class Film : public Video
{
    int numberChapters;
    int *durationChapters;
public:
    /**
     * @brief Film Film constructor
     * @param name
     * @param fileName
     * @param duration
     * @param numberChapters
     */
    Film(string name, string fileName, int duration, int numberChapters)
        : Video(name, fileName, duration)
    {
        durationChapters = nullptr;
    }
    /**
     * @brief Film copy constructor
     * @param other
     */
    Film(const Film &other)
        : Video(other) {
        numberChapters = other.numberChapters;
        if(other.durationChapters != nullptr)
            this->setDurationChapters(other.durationChapters, other.numberChapters);
    }

    /**
     * @brief operator = redefining the operator= to handle deep copies
     * @param other
     * @return
     */
    // comment this if you want to allow "shallow" copy
    Film& operator=(const Film &other) {
        Video::operator=(other);
        numberChapters = other.numberChapters;
        delete durationChapters;
        if(other.durationChapters)
            this->setDurationChapters(other.durationChapters, other.numberChapters);
        else
            durationChapters = nullptr;
        return *this;
    }

    ~Film() {
        delete[] durationChapters;
    }
    /**
     * @brief printVariables
     * @param flux
     */
    void printVariables(ostream & flux) const override {
        // Afin d'éviter les \n dans les sockets, on les remplace par "||". Il faudra les parser par la suite côté client

        Video::printVariables(flux);
        flux << "Number of chapters : " << numberChapters << "||";
        for(int i = 0; i < numberChapters; i++) {
            flux << "Duration chapter #" << i + 1 << ": " << durationChapters[i] << "||";
        }
    }
    /**
     * @brief setDurationChapters sets
     * @param durationChapters
     * @param numberChapters
     */
    void setDurationChapters(const int *durationChapters, int numberChapters) {
        this->numberChapters = numberChapters;

        delete[] this->durationChapters;
        this->durationChapters = nullptr;

        if(numberChapters > 0)
            this->durationChapters = new int[numberChapters];

        for(int i = 0; i < numberChapters; i++) {
            this->durationChapters[i] = durationChapters[i];
        }

    }
    /**
     * @brief getNumberChapters
     * @return
     */
    int getNumberChapters() const {
        return numberChapters;
    }

    /**
     * @brief getDurationChapters
     * @return
     */

    const int* getDurationChapters() const{
        return durationChapters;
    }
    /**
     * @brief getChapter
     * @param index
     * @return
     */
    int getChapter(int index) {
        if(index >= numberChapters)
            return -1; // out of bound
        else
            return durationChapters[index];

    }
    /**
     * @brief getType
     * @return
     */
    string getType() const override {
        return string("Film");
    }


};

#endif // FILM_H
