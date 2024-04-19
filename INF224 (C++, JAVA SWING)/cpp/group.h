#ifndef GROUP_H
#define GROUP_H

#include <list>
#include <string>
#include <memory>
#include "multimedia.h"
#include "video.h"
using namespace std;

/**
 * @brief The Group class
 */

class Group : public list<shared_ptr<Multimedia>>
{
    string name;

public:
    Group() {}
    /**
     * @brief Group
     * @param name
     */
    Group(string name) : name(name) {}
    ~Group() {
        cout << "Group " << name << " is dead ! " << endl;
        for(auto it = this->begin(); it != this->end(); it++) {
//            debug(it->use_count());
            it->reset();
        }
    }
    /**
     * @brief getName returns the name of the group
     * @return
     */
    string getName() const {
        return name;
    }

    /**
     * @brief printGroup call printVariables on the list of Multimedia elements in the same group
     */

    void printGroup(ostream &out) {
        out << "--------------------------------------||";
        out << "Printing group :" << name << "||";
        out << "--------------------------------------||";

        for(auto it = this->begin(); it != this->end(); it++) {
            (*it)->printVariables(out);
        }
    }

    /**
     * @brief setName sets the name of the group
     * @param name
     */
    void setName(string name) {
        this->name = name;
    }
};

#endif // GROUP_H
