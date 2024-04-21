#ifndef GROUP_H
#define GROUP_H

#include "Media.h"

#include <list>
#include <string>
#include <memory>

using namespace std;
using MediaPtr = shared_ptr<Media>;

class Group : public list<MediaPtr> {
    friend class Master;

    private :
    string name {};
    Group(string name) : list<MediaPtr>(), name{name} {}
    Group() {}

    public :
    string get_name() const {return name;}
    void print_values(ostream& stream) const {
        for(auto & it : *this) it->print_values(stream);
    }
};

#endif