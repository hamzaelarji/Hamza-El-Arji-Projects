#ifndef PHOTO_H
#define PHOTO_H

#include "Media.h"

class Photo : public Media {
    
    friend class Master;
    
    private:
    int width {};
    int height {};
    Photo(string name, string pathname, int width, int height) : Media(name, pathname), width{width}, height{height} {}
    Photo() {}

    public:
    virtual ~Photo() {cout << "I die Photo" << endl;}
    int get_width() const {return width;}
    int get_height() const {return height;}
    void set_width(int w) {width = w;}
    void set_height(int h) {height = h;}
    void print_values(ostream& stream) const { Media::print_values(stream); stream << " width : " << width << " height : " << height;}    
    void play_media() const { system(("imagej " + pathname + "/" + name + " &").c_str()); }
};


#endif