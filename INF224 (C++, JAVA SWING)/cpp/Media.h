#ifndef MEDIA_H
#define MEDIA_H

#include <string>
#include <iostream>
using namespace std;

class Media {
    protected:
    string name {};
    string pathname {};

    public:
    Media(string name, string pathname) : name{name}, pathname{pathname} {}
    Media() {}
    virtual ~Media() = default;
    string get_name() const;
    string get_pathname() const;
    void set_name(string name);
    void set_pathname(string pathname);
    virtual void print_values(ostream& stream) const;
    virtual void play_media() const = 0;

};


#endif