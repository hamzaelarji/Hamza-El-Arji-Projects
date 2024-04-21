#include "Film.h"

Film::Film(string name, string pathname, int duration, int number_chapter, int * _chapters)
: Video(name, pathname, duration) {

    set_chapters(_chapters, number_chapter);

}

void Film::set_chapters(int const * _chapters, int _number_chapter) { // vérifier que les conditions sont vérifiées
    number_chapter = _number_chapter;

    delete[] chapters;
    chapters = new int[number_chapter];

    for(int i = 0; i < number_chapter; i++) {
        chapters[i] = _chapters[i];
    }
}