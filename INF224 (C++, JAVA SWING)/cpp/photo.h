#ifndef PHOTO_H
#define PHOTO_H

#include "multimedia.h"

using namespace std;
/**
 * @brief The Photo class
 */
class Photo : public Multimedia {
    double latitude;
    double longitude;
public:
    Photo(string name, string fileName, double latitude, double longitude) :
        Multimedia(name, fileName), latitude(latitude), longitude(longitude) {
    }

    /**
     * @brief printVariables affiche les variables d'une photo
     * @param flux
     */
    void printVariables(ostream & flux) const override {
        Multimedia::printVariables(flux);
        flux << "latitude : " << latitude << "||";
        flux << "longitude : " << longitude <<"||";
    }

    /**
     * @brief play plays the photo
     */
    void play() const override {
        string command = "imagej " + fileName + " &";
        cout << "IMAGE" << endl;
        system(command.c_str());
    }

    /**
     * @brief getType gets the type of the Multimedia
     * @return
     */
    string getType() const override {
        return string("Photo");
    }

    /**
     * @brief setLattitude
     * @param lattitude
     */
    void setLattitude(double lattitude) {
        this->latitude = latitude;
    }

    /**
     * @brief setLongitude
     * @param longitude
     */
    void setLongitude(double longitude) {
        this->longitude = longitude;
    }

    /**
     * @brief getLatitude
     * @return
     */
    int getLatitude() const {
        return latitude;
    }

    /**
     * @brief getLongitude
     * @return
     */
    int getLongitude() const {
        return longitude;
    }

};

#endif // PHOTO_H
