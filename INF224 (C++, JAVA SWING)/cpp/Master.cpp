#include "Master.h"

PhotoPtr Master::createPhoto(string name, string photo_name, string pathname, int width, int height) {
    PhotoPtr photo(new Photo(photo_name, pathname, width, height));
    medias[name] = photo;
    return photo;
}

VideoPtr Master::createVideo(string name, string video_name, string pathname, int duration) {
    VideoPtr video(new Video(video_name, pathname, duration));
    medias[name] = video;
    return video;
}

FilmPtr Master::createFilm(string name, string film_name, string pathname, int duration, int number_chapter, int * _chapters) {
    FilmPtr film(new Film(film_name, pathname, duration, number_chapter, _chapters));
    medias[name] = film;
    return film;
}

GroupPtr Master::createGroup(string name) {
    GroupPtr group(new Group());
    groups[name] = group;
    return group;
}

void Master::printObject(string name, ostream & stream) const {
    auto it = medias.find(name);
    if(it == medias.end()) {
        stream << "No media named " << name << " !";
        //stream << "No media named " << name << " !\n" << endl;
    } else {
        it->second->print_values(stream);
    }
}

void Master::printGroup(string name, ostream & stream) const {
    auto it = groups.find(name);
    if(it == groups.end()) {
        stream << "No group named " << name << " !";
        //stream << "No group named " << name << " !\n" << endl;
    } else {
        it->second->print_values(stream);
    }
}

void Master::play(string name) const {
    auto it = medias.find(name);
    if(it == medias.end()) {
        cout << "No media named " << name << " !";
        //stream << "No media named " << name << " !\n" << endl;
    } else {
        it->second->play_media();
    }
}

void Master::deleteObject(string name) {
    auto it_media = medias.find(name);
    if(it_media == medias.end()) {
        cout << "No media named " << name << " !\n" << endl;
    } else {
        for(auto it_group : groups) {
            GroupPtr group = it_group.second;
            MediaPtr val = it_media->second;
            auto found_it =find(group->begin(), group->end(), val);
            if(found_it != group->end()) {
                group->erase(found_it);
            }
        }
        medias.erase(name);
    }
}

void Master::deleteGroup(string name) {
    auto it = groups.find(name);
    if(it == groups.end()) {
        cout << "No group named " << name << " !\n" << endl;
    } else {
        groups.erase(name);
    }
}