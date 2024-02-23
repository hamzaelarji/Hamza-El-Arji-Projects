class Item {
    constructor(title, color, value) {
      this.title = title;
      this.color = color;
      this.value = value;
    }
  }

const baseUrl = "../.."; //http://localhost:8000

function getData() {
  return fetch(`${baseUrl}/Items`, {
      method: 'GET',
      headers: {
          'Content-Type': 'application/json',
      }
  })
  .then(response => {
      if (!response.ok) {
          throw new Error('Network response was not ok');
      }
      return response.json();  // Utilisez response.json() pour obtenir directement l'objet JSON
  })
  .catch(error => {
      console.error('Error during fetch:', error.message);
  });
}


function addData() {
    const title = document.getElementById('titleTF').value;
    const color = document.getElementById('colorTF').value;
    const value = parseFloat(document.getElementById('valueTF').value);
  
    const url = `${baseUrl}/add?title=${encodeURIComponent(title)}&value=${encodeURIComponent(value)}&color=${encodeURIComponent(color)}`;
    fetch(url, {
      method: 'GET',
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Data added successfully:', data);
      })
      .catch(error => {
        console.error('Error during fetch:', error.message);
      });
  }

  function clearData() {
    fetch(`${baseUrl}/clear`, {
      method: 'GET',
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Data cleared successfully:', data);
      })
      .catch(error => {
        console.error('Error during fetch:', error.message);
      });
  }

  function restoreData() {
    fetch(`${baseUrl}/restore`, {
      method: 'GET',
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Data restored successfully:', data);
      })
      .catch(error => {
        console.error('Error during fetch:', error.message);
      });
  }

  function removeElement(index) {
    fetch(`${baseUrl}/remove?index=${index}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Element removed successfully:', data);
      })
      .catch(error => {
        console.error('Error during fetch:', error.message);
      });
  }

  function printPie() {
    fetch(`${baseUrl}/PieCh`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('PieChart print successfully !');
      })
      .catch(error => {
        console.error('Error during fetch:', error.message);
      });
  }
  
  function constuctPieChart(data) {
    document.getElementById("pieChart").innerHTML = '';
    const totalValue = data.reduce((sum, item) => sum + item.value, 0);
    let startAngle = 0;
    for (const item of data) {
        const endAngle = startAngle + (item.value / totalValue) * 360;

        const startX = 150 + 100 * Math.cos((startAngle - 90) * (Math.PI / 180));
        const startY = 150 + 100 * Math.sin((startAngle - 90) * (Math.PI / 180));

        const endX = 150 + 100 * Math.cos((endAngle - 90) * (Math.PI / 180));
        const endY = 150 + 100 * Math.sin((endAngle - 90) * (Math.PI / 180));

        const largeArcFlag = item.value / totalValue > 0.5 ? 1 : 0;

        const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
        path.setAttribute("d", `M150,150 L${startX},${startY} A100,100 0 ${largeArcFlag},1 ${endX},${endY} Z`);
        path.setAttribute("fill", item.color);
        path.setAttribute("stroke", "#000");
        path.setAttribute("stroke-width", "2");

        document.getElementById("pieChart").appendChild(path);

        startAngle = endAngle;
    }
}

function generatePieChart() {
  fetch('http://localhost:8000/PieCh')
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.text(); // Utilisez response.text() si le serveur renvoie du SVG
    })
    .then(svgData => {
      // Insérer directement le SVG dans l'élément destiné à l'afficher
      document.getElementById("pieChart").innerHTML = svgData;
    })
    .catch(error => {
      console.error('Error during fetch:', error.message);
    });
}

