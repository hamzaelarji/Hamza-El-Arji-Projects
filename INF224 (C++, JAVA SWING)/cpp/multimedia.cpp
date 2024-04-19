#include "multimedia.h"

Multimedia::Multimedia()
{

}


void Multimedia::printVariables(ostream &flux) const {
    flux << "-----------------------------" << "||";
    flux << "Printing variables" << "||";
    flux << "-----------------------------" << "||";
    flux << "name : " << name << "||";
    flux << "fileName : " << fileName << "||";
}
