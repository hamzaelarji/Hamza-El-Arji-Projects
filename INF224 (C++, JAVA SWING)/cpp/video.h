#ifndef VIDEO_H
#define VIDEO_H

#include "Media.h"

class Video : public Media {
    
    friend class Master;
    
    protected:

    int duration {};
    
    Video(string name, string pathname, int duration) : Media(name, pathname), duration{duration} {}
    Video() {}

    public:
    
    virtual ~Video() {cout << "I die Video" << endl;}
    int get_duration() const {return duration;}
    void set_duration(int d) {duration = d;}
    void print_values(ostream& stream) const {
        Media::print_values(stream);
        stream << " duration : " << duration;
        //stream << "duration : " << duration << "\n"
        //<< std::endl;
    } //il faut rajouter l'impression de la durée
    void play_media() const { system(("mpv " + pathname + "/" + name + " &").c_str()); }

};


#endif