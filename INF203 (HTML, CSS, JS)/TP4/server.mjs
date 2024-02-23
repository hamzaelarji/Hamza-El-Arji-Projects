
"use strict";

import { createServer } from "http";
import { readFile, existsSync } from "fs";
import { resolve, join, extname } from "path";
import { URL } from "url"

// MIME types for common file formats
const mimeTypes = {
    '.html': 'text/html',
    '.css': 'text/css',
    '.mjs': 'application/javascript',
    '.json': 'application/json', // Ajoutez cette ligne pour les fichiers JSON
    '.png': 'image/png',
    '.jpg': 'image/jpeg',
    '.jpeg': 'image/jpeg',
    '.gif': 'image/gif',
    '.svg': 'image/svg+xml',
    '.txt': 'text/plain'
};

// Conteneur pour stocker les noms d'utilisateurs
let users = [];

function sanitizeInput(input) {
    // Nettoyer l'entrée en remplaçant < et > par _
    return input.replace(/[<>]/g, "_");
}

function webserver(request, response) {
    try {
        const baseURL = `http://${request.headers.host}/`;
        const reqUrl = new URL(request.url, baseURL);

        if (request.url === "/end") {
            response.setHeader("Content-Type", "text/html; charset=utf-8");
            response.end("<!doctype html><html><body>The server will stop now.</body></html>");
            console.log("Server is stopping as requested by /end URL.");
            process.exit(0);
            return; // Assurez-vous de retourner ici
        }

        if (request.url.startsWith("/www/")) {
            const safeSuffix = request.url.slice(5).split('?')[0]; // Enlever les paramètres de requête
            const safePath = resolve(join(process.cwd(), safeSuffix));

            if (!safePath.startsWith(process.cwd())) {
                response.writeHead(403);
                response.end("Access Denied!");
                return; // Retour après avoir envoyé la réponse
            }

            if (existsSync(safePath)) {
                // Utilisation de `extname` pour obtenir l'extension du fichier, incluant le point
                const extension = extname(safePath);
                
                readFile(safePath, (err, data) => {
                    if (err) {
                        response.writeHead(500);
                        response.end("Server Error: Unable to read file.");
                    } else {
                        // Vérification et application du type MIME correct
                        // Assurez-vous que le type MIME par défaut n'est utilisé que si l'extension n'est pas reconnue
                        const mimeType = mimeTypes[extension] || 'application/octet-stream'; // Fallback plus général que 'text/plain'
                        response.writeHead(200, { 'Content-Type': mimeType });
                        response.end(data);
                    }
                });
            } else {
                response.writeHead(404);
                response.end("404 Not Found");
                return; // Retour après avoir envoyé la réponse
            }
        } else if (reqUrl.pathname === "/hallo") {
            const name = reqUrl.searchParams.get("name");
            response.setHeader("Content-Type", "text/html; charset=utf-8");
            response.end(`<!doctype html><html><body>hallo ${name}</body></html>`);
        }
        else if (reqUrl.pathname === "/clear") {
            users.length = 0; // Efface la liste des utilisateurs
            response.setHeader("Content-Type", "text/html; charset=utf-8");
            response.end("<!doctype html><html><body>Memory of past salut requests cleared.</body></html>");
        }
        
        else if (reqUrl.pathname === "/salut") {
            const user = sanitizeInput(reqUrl.searchParams.get("user"));
            // Générer la réponse sans ajouter `user` à `users` avant de vérifier si d'autres ont visité
            const responseText = `salut ${user}, the following users have already visited this page: ${users.join(', ')}`;
        
            // Ajouter l'utilisateur après avoir généré la réponse pour éviter de l'inclure
            // dans la liste si `/clear` vient d'être exécuté
            if (user) {
                users.push(user);
            }
        
            response.setHeader("Content-Type", "text/html; charset=utf-8");
            response.end(`<!doctype html><html><body>${responseText}</body></html>`);
        }
        
        else {
            response.setHeader("Content-Type", "text/html; charset=utf-8");  
            response.end("<!doctype html><html><body>Server works. Use /www/ to access files.</body></html>");
            return; // Retour après la réponse par défaut
        }
    } catch (error) {
        console.error("An error occurred:", error);
        // Ne tentez de répondre que si les en-têtes n'ont pas encore été envoyés
        if (!response.headersSent) {
            response.writeHead(500);
            response.end("Internal Server Error");
        }
    }
}

// Server object creation
const server = createServer(webserver);

// Start the server on a port number given on the command line, or default to 8000
const port = process.argv[2] || 8000;
server.listen(port, () => {
    console.log(`Server running at http://localhost:${port}/`);
});
