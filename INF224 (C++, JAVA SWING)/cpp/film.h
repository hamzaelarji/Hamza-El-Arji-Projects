#ifndef FILM_H
#define FILM_H

#include "Video.h"

class Film : public Video {
    
    friend class Master;
    
    private:

    int number_chapter {};
    int * chapters {};
    Film(string name, string pathname, int duration, int number_chapter, int * _chapters);
    Film() {}

    public:
    
    ~Film() {delete[] chapters;}
    int get_number_chapter() const {return number_chapter;}
    int const * get_chapters() const {return chapters;}
    void set_chapters(int const * chapters, int number_chapter);

    void print_values(ostream& stream) const {
        Video::print_values(stream);
        stream
        << " number of chapters : " << number_chapter
        << " duration of chapters : ";
        for(int i = 0; i < number_chapter; i++) {
            stream
            << " chapter " << i << " : " << chapters[i];
        }
    }

};


#endif