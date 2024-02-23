import http from 'http';
import express from 'express';
import fs from 'fs';
import morgan from 'morgan';
import { promises as fsPromises } from 'fs';

const port = process.argv[2] || 8000;
const logFormat = ':method :url :status :res[content-length] - :response-time ms';

const server = http.createServer((req, res) => {
  morgan(logFormat)(req, res, () => {});

  if (req.url === '/' && req.method === 'GET') {
    res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8' });
    res.end('Hi');
  }  else if (req.url === '/kill' && req.method === 'GET') {
    res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8' });
    res.end('Server will stop now.');

    server.close(() => {
      console.log('Server stopped.');
      process.exit(0);
    });
  } else if (req.url === '/clean' && req.method === 'GET') {
    reloadDb()
      .then(() => {
        res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8' });
        res.end('db.json reloaded');
      })
      .catch((error) => {
        console.error('Error during reload:', error.message);
        res.writeHead(500, { 'Content-Type': 'text/plain; charset=utf-8' });
        res.end('Internal Server Error');
      });
  } else if (req.url === '/papercount' && req.method === 'GET') {
    fs.readFile('db.json', 'utf-8', (err, data) => {
      if (err) {
        res.writeHead(500, { 'Content-Type': 'text/plain' });
        res.end('Internal Server Error');
        return;
      }

      const publications = JSON.parse(data);

      const count = publications.length;

      res.writeHead(200, { 'Content-Type': 'text/plain' });
      res.end(`${count}`);
    });
  }else if (req.url.startsWith('/author/')) {
    const authorName = req.url.substring('/author/'.length);
  
    fs.readFile('db.json', 'utf-8', (err, data) => {
      if (err) {
        res.writeHead(500, { 'Content-Type': 'text/plain' });
        res.end('Internal Server Error');
        return;
      }
  
      const publications = JSON.parse(data);
  
      const matchingPublications = publications.filter((pub) =>
      pub.authors.some(author => author.toLowerCase().includes(authorName))
    );

      const count = matchingPublications.length;
  
      res.writeHead(200, { 'Content-Type': 'text/plain' });
      res.end(`${count}`);
    });
  } else if (req.url.startsWith('/papersdesc/')) {
    const authorName = req.url.substring('/papersdesc/'.length);
  
    fs.readFile('db.json', 'utf-8', (err, data) => {
        if (err) {
            res.writeHead(500, { 'Content-Type': 'text/plain' });
            res.end('Internal Server Error');
            return;
        }
    
        const publications = JSON.parse(data);
    
        const matchingPublications = publications.filter((pub) =>
        pub.authors.some(author => author.toLowerCase().includes(authorName))
      );
    
      const descriptors = matchingPublications.map(publication => publication);
    

        res.writeHead(200, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify(descriptors));
    });
}else if (req.url.startsWith('/titlelist/')) {
  const authorName = req.url.substring('/titlelist/'.length);

  fs.readFile('db.json', 'utf-8', (err, data) => {
      if (err) {
          res.writeHead(500, { 'Content-Type': 'text/plain' });
          res.end('Internal Server Error');
          return;
      }
  
      const publications = JSON.parse(data);
  
      const matchingPublications = publications.filter((pub) =>
      pub.authors.some(author => author.toLowerCase().includes(authorName))
    );
  
      const titles = matchingPublications.map(publication => publication.title);

      res.writeHead(200, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify(titles));
  });
} else if (req.method === 'GET' && req.url.startsWith('/publication/')) {
  const keyy = req.url.substring('/publication/'.length);

  fs.readFile('db.json', 'utf-8', (err, data) => {
      if (err) {
          res.writeHead(500, { 'Content-Type': 'text/plain' });
          res.end('Internal Server Error');
          return;
      }
  
      const publications = JSON.parse(data);
  
      const matchingPublication = publications.find((pub) =>
      pub.key === keyy
    );

    if (matchingPublication) {
      res.writeHead(200, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify(matchingPublication));
    } else {
      res.writeHead(404, { 'Content-Type': 'text/plain' });
      res.end('Publication not found');
    }
  });
}else if (req.method === 'DELETE' && req.url.startsWith('/publication/')) {
  const keyToRemove = req.url.substring('/publication/'.length).toLowerCase();

  fs.readFile('db.json', 'utf-8', (err, data) => {
    if (err) {
      res.writeHead(500, { 'Content-Type': 'text/plain' });
      res.end('Internal Server Error');
      return;
    }

    let publications = JSON.parse(data);

    const indexToRemove = publications.findIndex(pub => pub.key.toLowerCase() === keyToRemove);

    if (indexToRemove !== -1) {
      publications.splice(indexToRemove, 1);

      fs.writeFile('db.json', JSON.stringify(publications, null, 2), (writeErr) => {
        if (writeErr) {
          res.writeHead(500, { 'Content-Type': 'text/plain' });
          res.end('Internal Server Error');
          return;
        }

        res.writeHead(200, { 'Content-Type': 'text/plain' });
        res.end(`Publication with key '${keyToRemove}' deleted`);
      });
    } else {
      res.writeHead(404, { 'Content-Type': 'text/plain' });
      res.end(`Publication with key '${keyToRemove}' not found`);
    }
  });
}else if (req.method === 'POST' && req.url === '/publication') {
  const imaginaryPublication = {"key":"imaginary","title":"fun","journal":"pifpoche","year":"1960","authors":["dufourd"]};

  fs.readFile('db.json', 'utf-8', (err, data) => {
    if (err) {
      res.writeHead(500, { 'Content-Type': 'text/plain' });
      res.end('Internal Server Error');
      return;
    }

    const publications = JSON.parse(data);
    publications.push(imaginaryPublication);

    fs.writeFile('db.json', JSON.stringify(publications, null, 2), (writeErr) => {
      if (writeErr) {
        res.writeHead(500, { 'Content-Type': 'text/plain' });
        res.end('Internal Server Error');
        return;
      }

      res.writeHead(200, { 'Content-Type': 'text/plain' });
      res.end('Imaginary publication added successfully');
    });
  });
}else if (req.method === 'PUT' && req.url.startsWith('/publication/')) {
  const keyToUpdate = req.url.substring('/publication/'.length).toLowerCase();
  let newPublication = {"key":"imaginary","title":"morefun","journal":"tintin","year":"1960","authors":["dufourd"]};

    fs.readFile('db.json', 'utf-8', (err, data) => {
      if (err) {
        res.writeHead(500, { 'Content-Type': 'text/plain' });
        res.end('Internal Server Error');
        return;
      }

      let publications = JSON.parse(data);

      const indexToUpdate = publications.findIndex(pub => pub.key.toLowerCase() === keyToUpdate);

      if (indexToUpdate !== -1) {
        publications[indexToUpdate] = newPublication;

        fs.writeFile('db.json', JSON.stringify(publications, null, 2), (writeErr) => {
          if (writeErr) {
            res.writeHead(500, { 'Content-Type': 'text/plain' });
            res.end('Internal Server Error');
            return;
          }

          res.writeHead(200, { 'Content-Type': 'text/plain' });
          res.end(`Publication with key '${keyToUpdate}' updated`);
        });
      } else {
        res.writeHead(404, { 'Content-Type': 'text/plain' });
        res.end(`Publication with key '${keyToUpdate}' not found`);
      }
    });
} else {
  res.writeHead(404, { 'Content-Type': 'text/plain' });
  res.end('Not Found');
}
})

server.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
});

async function reloadDb() {
  const dbContent = await fsPromises.readFile('db.json', 'utf-8');
}
