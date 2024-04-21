#include "Media.h"

string Media::get_name() const{
    return name;
}

string Media::get_pathname() const {
    return pathname;
}

void Media::set_name(string _name) {
    name = _name;
}

void Media::set_pathname(string _pathname) {
    pathname = _pathname;
}

void Media::print_values(ostream& stream) const {
    stream
    << " name : " << name
    << " pathname : " << pathname;
    
    //stream
    //<< "name : " << name << "\n"
    //<< "pathname : " << pathname
    //<< std::endl;
}