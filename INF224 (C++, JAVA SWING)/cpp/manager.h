#ifndef MANAGER_H
#define MANAGER_H

#include <map>
#include <memory>
#include <sstream>
#include <ostream>
#include "multimedia.h"
#include "group.h"
#include "photo.h"
#include "video.h"
#include "film.h"
#include "tcpserver.h"

using namespace std;
using namespace cppu;
/**
 * @brief The Manager class
 */
class Manager
{
private:
    map <string, shared_ptr <Multimedia> > multimedia_table;
    map <string, shared_ptr <Group> > group_table;

public:
    Manager();

    /**
     * @brief createPhoto
     * @param name
     * @param fileName
     * @param latitude
     * @param longitude
     * @return
     */
    shared_ptr<Photo> createPhoto(string name, string fileName, double latitude, double longitude);
    /**
     * @brief createVideo
     * @param name
     * @param fileName
     * @param duration
     * @return
     */
    shared_ptr<Video> createVideo(string name, string fileName, int duration);
    /**
     * @brief createFilm
     * @param name
     * @param fileName
     * @param duration
     * @param numberChapters
     * @return
     */
    shared_ptr<Film> createFilm(string name, string fileName, int duration, int numberChapters);
    /**
     * @brief createGroup
     * @param name
     * @return
     */
    shared_ptr<Group> createGroup(string name);

    /**
     * @brief findMultimedia
     * @param name
     * @return
     */
    shared_ptr<Multimedia> findMultimedia(string name);
    /**
     * @brief findGroup
     * @param name
     * @return
     */
    shared_ptr<Group> findGroup(string name);

    /**
     * @brief printVariables
     * @param name
     * @param stream_out
     */
    void printVariables(string name, ostream& stream_out);
    /**
     * @brief play
     * @param name
     */
    void play(string name);
    /**
     * @brief listAllMultimedia
     * @return
     */
    string listAllMultimedia();

    /**
     * @brief processRequest
     * @param cnx
     * @param request
     * @param response
     * @return
     */
    bool processRequest(TCPConnection& cnx, const string& request, string& response);
};

#endif // MANAGER_H
