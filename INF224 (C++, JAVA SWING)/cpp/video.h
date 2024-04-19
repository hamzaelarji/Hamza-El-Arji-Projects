#ifndef VIDEO_H
#define VIDEO_H

#include "multimedia.h"

using namespace std;

/**
 * @brief The Video class
 */
class Video : public Multimedia {
protected:
    int duration;
public:
    Video(string name, string fileName, int duration) :
        Multimedia(name, fileName), duration(duration) {

    }
    virtual ~Video() {}

    /**
     * @brief printVariables
     * @param flux
     */
    void printVariables(ostream & flux) const override {
        Multimedia::printVariables(flux);
        flux << "duration : " << duration << "||";
    }

    /**
     * @brief play
     */
    void play() const override {
        string command = "mpv " + fileName + " &";
        system(command.c_str());
    }
    /**
     * @brief getType
     * @return
     */
    string getType() const override {
         return string("Video");
    }
    /**
     * @brief setDuree
     * @param duration
     */
    void setDuree(int duration) {
        this->duration = duration;
    }

    /**
     * @brief getDuration
     * @return
     */
    int getDuration() const {
        return duration;
    }


};

#endif // VIDEO_H

