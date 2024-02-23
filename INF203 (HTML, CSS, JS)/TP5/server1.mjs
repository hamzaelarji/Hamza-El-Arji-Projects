
"use strict";

import { createServer } from "http";
import { promises as fsPromises } from "fs";
import { fileURLToPath } from "url";
import { dirname, resolve,normalize } from "path";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);
const filePath = resolve(__dirname, 'storage.json');

async function webserver(request, response) {

  response.setHeader('Access-Control-Allow-Origin', '*');
  response.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
  response.setHeader('Access-Control-Allow-Headers', 'Content-Type');

  if (request.method === 'OPTIONS') {
    response.writeHead(200);
    response.end();
    return;
  }

const url = new URL(request.url, `http://${request.headers.host}`);

if (url.pathname === "/") {
  response.end("<!doctype html><html><body>Server is working</body></html>");
  }
else if (url.pathname.startsWith("/WWW/")) {

    const normalizedPath = normalize(resolve(__dirname, url.pathname.slice("/WWW/".length)));
    if (normalizedPath !== __dirname && !normalizedPath.startsWith(__dirname + '/')) {
      response.writeHead(403, { "Content-Type": "text/plain" });
      response.end("Forbidden");
      return;
    }
    try {
      const fileContent = await fsPromises.readFile(normalizedPath, "utf-8");

      const contentType = getContentType(normalizedPath)+"; charset=utf-8";
      response.setHeader("Content-Type", contentType);
      response.end(fileContent);
    } catch (error) {
      if (error.code === "ENOENT") {
        response.writeHead(404, { "Content-Type": "text/plain" });
        response.end("File not found");
      } else {
        response.writeHead(500, { "Content-Type": "text/plain" });
        response.end("Internal Server Error");
      }
    }
  }
  else if (url.pathname === "/end" && request.method === "GET") {
    response.writeHead(200, { "Content-Type": "text/plain; charset=utf-8" });
    response.end("The server will stop now.");

    server.close(() => {
      console.log("Server stopped.");
      process.exit(0);
    });
} else if (url.pathname === "/Items" && request.method === "GET") {
  try {
    const filePath = resolve(__dirname, 'storage.json');
    let jsonData = await fsPromises.readFile(filePath, 'utf-8');
    jsonData = JSON.parse(jsonData);

    response.writeHead(200, { "Content-Type": "application/json; charset=utf-8" });
    response.end(JSON.stringify(jsonData));
  } catch (error) {
    response.writeHead(500, { "Content-Type": "text/plain; charset=utf-8" });
    response.end("Internal Server Error");
  }
}
else if (url.pathname === "/add" && request.method === "GET") {
  try {
      const title = url.searchParams.get('title');
      const color = url.searchParams.get('color');
      const value = parseFloat(url.searchParams.get('value'));

      let jsonData = await fsPromises.readFile(filePath, 'utf-8');
      jsonData = JSON.parse(jsonData);

      const newData = { title, color, value };

      jsonData.push(newData);

      await fsPromises.writeFile(filePath, JSON.stringify(jsonData, null, 2), 'utf-8');

      response.writeHead(200, { "Content-Type": "application/json; charset=utf-8" });
      response.end(JSON.stringify({ message: 'Data was added successfully.' }));
  } catch (error) {
      response.writeHead(500, { "Content-Type": "text/plain; charset=utf-8" });
      response.end("Internal Server Error");
  }
} else if (url.pathname === "/clear" && request.method === "GET") {
  try {

    const newContent = JSON.stringify([{ title: 'empty', color: 'red', value: 1 }], null, 2);

    await fsPromises.writeFile(filePath, newContent, 'utf-8');

    response.writeHead(200, { "Content-Type": "application/json; charset=utf-8" });
    response.end(JSON.stringify({ message: 'Data was clear successfully.' }));
  } catch (error) {
    response.writeHead(500, { "Content-Type": "text/plain; charset=utf-8" });
    response.end("Internal Server Error");
  }
} else if (url.pathname === "/restore" && request.method === "GET") {
  try {

    const restoredData = [{"title": "foo", "color": "red", "value": 20}, {"title": "bar", "color": "ivory", "value": 100}];

    await fsPromises.writeFile(filePath, JSON.stringify(restoredData, null, 2), 'utf-8');

    response.writeHead(200, { "Content-Type": "application/json; charset=utf-8" });
    response.end(JSON.stringify({ message: 'Data was restored successfully.' }));
  } catch (error) {
    response.writeHead(500, { "Content-Type": "text/plain; charset=utf-8" });
    response.end("Internal Server Error");
  }
}else if (url.pathname === "/remove" && request.method === "GET") {
  try {
      const index = parseInt(url.searchParams.get('index'), 10);

      let jsonData = await fsPromises.readFile(filePath, 'utf-8');
      jsonData = JSON.parse(jsonData);

      if (index >= 0 && (index < jsonData.length || jsonData.length === 0)) {
          jsonData.splice(index, 1);

          await fsPromises.writeFile(filePath, JSON.stringify(jsonData, null, 2), 'utf-8');

          response.writeHead(200, { "Content-Type": "application/json; charset=utf-8" });
          response.end(JSON.stringify({ message: 'Element removed successfully.' }));
      } else {
          response.writeHead(400, { "Content-Type": "text/plain; charset=utf-8" });
          response.end('Invalid index.');
      }
  } catch (error) {
      response.writeHead(500, { "Content-Type": "text/plain; charset=utf-8" });
      response.end("Internal Server Error");
  }
}else if (url.pathname === "/PieCh" && request.method === "GET") {
  try {
    const filePath = resolve(__dirname, 'storage.json');
    let jsonData = await fsPromises.readFile(filePath, 'utf-8');
    jsonData = JSON.parse(jsonData);

    response.writeHead(200, { "Content-Type": "image/svg+xml; charset=utf-8" });
    response.end(JSON.stringify(jsonData));
  } catch (error) {
    response.writeHead(500, { "Content-Type": "text/plain; charset=utf-8" });
    response.end("Internal Server Error");
  }
}
else {
  response.writeHead(403, { "Content-Type": "text/plain" });
  response.end("Forbidden");
}}


function getContentType(filePath) {
  const ext = filePath.split(".").pop().toLowerCase();
  switch (ext) {
    case "html":
      return "text/html";
    case "mjs":
      return "text/javascript";
    case "css":
      return "text/css";
    case "png":
      return "image/png";
    case "jpg":
      return "image/jpg";
    case "jpeg":
      return "image/jpeg";
    case "svg":
      return "image/svg+xml";
    case "json":
      return "application/json; charset=utf-8";
    default:
      return "application/octet-stream";
  }
}

const port = process.argv[2] || 8000;

const server = createServer(webserver);

server.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
});