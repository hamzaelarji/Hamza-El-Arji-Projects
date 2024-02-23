CREATE TRIGGER update_total_CO2_update_produits
AFTER UPDATE OF CO2 ON Total_produits
FOR EACH ROW
BEGIN
    UPDATE Utilisateurs
    SET Total_CO2 = Total_CO2 - OLD.CO2 + NEW.CO2 WHERE Id_user = OLD.Id_user;
END;

CREATE TRIGGER update_total_CO2_insert_produits
AFTER INSERT ON Total_produits
FOR EACH ROW
BEGIN
    UPDATE Utilisateurs
    SET Total_CO2 = Total_CO2 + NEW.CO2 WHERE Id_user = NEW.Id_user;
END;

CREATE TRIGGER update_total_CO2_delete_produits
AFTER DELETE ON Total_produits
FOR EACH ROW
BEGIN
    UPDATE Utilisateurs
    SET Total_CO2 = Total_CO2 - OLD.CO2 WHERE Id_user = OLD.Id_user;
END;



CREATE TRIGGER update_total_CO2_update_trajets
AFTER UPDATE OF CO2 ON Total_trajets
FOR EACH ROW
BEGIN
    UPDATE Utilisateurs
    SET Total_CO2 = Total_CO2 - OLD.CO2 + NEW.CO2 WHERE Id_user = OLD.Id_user;
END;

CREATE TRIGGER update_total_CO2_insert_trajets
AFTER INSERT ON Total_trajets
FOR EACH ROW
BEGIN
    UPDATE Utilisateurs
    SET Total_CO2 = Total_CO2 + NEW.CO2 WHERE Id_user = NEW.Id_user;
END;

CREATE TRIGGER update_total_CO2_delete_trajets
AFTER DELETE ON Total_trajets
FOR EACH ROW
BEGIN
    UPDATE Utilisateurs
    SET Total_CO2 = Total_CO2 - OLD.CO2 WHERE Id_user = OLD.Id_user;
END;