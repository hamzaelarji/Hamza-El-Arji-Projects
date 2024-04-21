#ifndef MASTER_H
#define MASTER_H

#include <string>
#include <memory>
#include <map>
#include <algorithm>

#include "Film.h"
#include "Photo.h"
#include "Group.h"

using namespace std;

using PhotoPtr = shared_ptr<Photo>;
using VideoPtr = shared_ptr<Video>;
using FilmPtr = shared_ptr<Film>;
using GroupPtr = shared_ptr<Group>;
using MediaPtr = shared_ptr<Media>;
using dicMedia = map<string, MediaPtr>;
using dicGroup = map<string, GroupPtr>;

class Master {

    private :
    dicMedia medias {};
    dicGroup groups {};

    public :
    Master() {}
    ~Master() {}
    PhotoPtr createPhoto(string name, string photo_name, string pathname, int width, int height);
    VideoPtr createVideo(string name, string video_name, string pathname, int duration);
    FilmPtr createFilm(string name, string film_name, string pathname, int duration, int number_chapter, int * _chapters);
    GroupPtr createGroup(string name);
    void printObject(string name, ostream & stream) const;
    void printGroup(string name, ostream & stream) const;
    void play(string name) const;
    void deleteObject(string name);
    void deleteGroup(string name);
};

#endif