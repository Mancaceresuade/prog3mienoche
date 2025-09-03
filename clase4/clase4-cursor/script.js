// Variables globales
let cities = [];
let graph = {};
let adjacencyMatrix = [];

// Clase para representar una ciudad
class City {
    constructor(name, x, y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}

// Clase para representar una conexión entre ciudades
class Connection {
    constructor(from, to, cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

// Función para generar ciudades aleatorias
function generateCities() {
    const cityCount = parseInt(document.getElementById('cityCount').value);
    const cityNamesInput = document.getElementById('cityNames').value;
    
    if (cityCount < 3 || cityCount > 10) {
        alert('El número de ciudades debe estar entre 3 y 10');
        return;
    }
    
    // Parsear nombres de ciudades
    let cityNames = cityNamesInput.split(',').map(name => name.trim());
    
    // Si no hay suficientes nombres, generar nombres genéricos
    while (cityNames.length < cityCount) {
        cityNames.push(`Ciudad${cityNames.length + 1}`);
    }
    
    // Limitar a la cantidad especificada
    cityNames = cityNames.slice(0, cityCount);
    
    // Generar ciudades con posiciones aleatorias
    cities = [];
    for (let i = 0; i < cityCount; i++) {
        const x = Math.random() * 400 + 50;
        const y = Math.random() * 200 + 50;
        cities.push(new City(cityNames[i], x, y));
    }
    
    // Generar grafo completo con costos aleatorios
    generateGraph();
    
    // Actualizar visualización
    updateVisualization();
    updateAdjacencyList();
}

// Función para generar el grafo completo
function generateGraph() {
    graph = {};
    adjacencyMatrix = [];
    
    // Inicializar matriz de adyacencia
    for (let i = 0; i < cities.length; i++) {
        adjacencyMatrix[i] = [];
        for (let j = 0; j < cities.length; j++) {
            adjacencyMatrix[i][j] = 0;
        }
    }
    
    // Generar conexiones entre todas las ciudades
    for (let i = 0; i < cities.length; i++) {
        graph[cities[i].name] = [];
        
        for (let j = 0; j < cities.length; j++) {
            if (i !== j) {
                // Calcular distancia euclidiana y agregar factor de terreno aleatorio
                const distance = Math.sqrt(
                    Math.pow(cities[i].x - cities[j].x, 2) + 
                    Math.pow(cities[i].y - cities[j].y, 2)
                );
                
                // Factor de terreno aleatorio (1.0 a 2.5)
                const terrainFactor = 1.0 + Math.random() * 1.5;
                
                // Costo = distancia * factor de terreno
                const cost = Math.round(distance * terrainFactor);
                
                graph[cities[i].name].push({
                    to: cities[j].name,
                    cost: cost
                });
                
                adjacencyMatrix[i][j] = cost;
            }
        }
    }
}

// Función para actualizar la visualización del grafo
function updateVisualization() {
    const container = document.getElementById('graphVisualization');
    container.innerHTML = '';
    
    if (cities.length === 0) {
        container.innerHTML = '<p>Genera ciudades para ver el grafo</p>';
        return;
    }
    
    // Crear nodos (ciudades)
    cities.forEach(city => {
        const node = document.createElement('div');
        node.className = 'graph-node';
        node.style.position = 'absolute';
        node.style.left = city.x + 'px';
        node.style.top = city.y + 'px';
        node.textContent = city.name.charAt(0);
        node.title = city.name;
        container.appendChild(node);
    });
    
    // Crear conexiones (líneas eléctricas)
    for (let i = 0; i < cities.length; i++) {
        for (let j = i + 1; j < cities.length; j++) {
            const connection = document.createElement('div');
            connection.className = 'graph-edge';
            connection.style.position = 'absolute';
            connection.style.left = (cities[i].x + cities[j].x) / 2 + 'px';
            connection.style.top = (cities[i].y + cities[j].y) / 2 + 'px';
            connection.textContent = adjacencyMatrix[i][j];
            connection.title = `${cities[i].name} ↔ ${cities[j].name}: ${adjacencyMatrix[i][j]}`;
            container.appendChild(connection);
        }
    }
}

// Función para actualizar la lista de adyacencia
function updateAdjacencyList() {
    const container = document.getElementById('adjacencyList');
    
    if (cities.length === 0) {
        container.innerHTML = '<p>No hay ciudades generadas</p>';
        return;
    }
    
    let html = '';
    cities.forEach(city => {
        html += `<strong>${city.name}:</strong><br>`;
        graph[city.name].forEach(connection => {
            html += `  → ${connection.to} (costo: ${connection.cost})<br>`;
        });
        html += '<br>';
    });
    
    container.innerHTML = html;
}

// Implementación del algoritmo de Prim
function primAlgorithm() {
    if (cities.length === 0) {
        alert('Primero genera ciudades');
        return;
    }
    
    const steps = [];
    const mst = [];
    const visited = new Set();
    const edges = [];
    
    // Agregar todas las aristas a la lista
    for (let i = 0; i < cities.length; i++) {
        for (let j = i + 1; j < cities.length; j++) {
            edges.push({
                from: cities[i].name,
                to: cities[j].name,
                cost: adjacencyMatrix[i][j]
            });
        }
    }
    
    // Ordenar aristas por costo
    edges.sort((a, b) => a.cost - b.cost);
    
    // Comenzar con la primera ciudad
    visited.add(cities[0].name);
    steps.push(`Iniciando con la ciudad: ${cities[0].name}`);
    
    // Iterar hasta conectar todas las ciudades
    while (visited.size < cities.length) {
        let minEdge = null;
        let minCost = Infinity;
        
        // Encontrar la arista de menor costo que conecte una ciudad visitada con una no visitada
        for (const edge of edges) {
            const fromVisited = visited.has(edge.from);
            const toVisited = visited.has(edge.to);
            
            if ((fromVisited && !toVisited) || (!fromVisited && toVisited)) {
                if (edge.cost < minCost) {
                    minCost = edge.cost;
                    minEdge = edge;
                }
            }
        }
        
        if (minEdge) {
            // Agregar la arista al MST
            mst.push(minEdge);
            visited.add(minEdge.from);
            visited.add(minEdge.to);
            
            steps.push(`Agregando conexión: ${minEdge.from} ↔ ${minEdge.to} (costo: ${minEdge.cost})`);
            steps.push(`Ciudades conectadas: ${Array.from(visited).join(', ')}`);
        }
    }
    
    return { mst, steps };
}

// Función para ejecutar el algoritmo de Prim
function runPrimAlgorithm() {
    if (cities.length === 0) {
        alert('Primero genera ciudades');
        return;
    }
    
    // Ejecutar algoritmo
    const { mst, steps } = primAlgorithm();
    
    // Mostrar pasos del algoritmo
    const stepsContainer = document.getElementById('algorithmSteps');
    stepsContainer.innerHTML = '<h3>Pasos del Algoritmo de Prim:</h3>';
    steps.forEach((step, index) => {
        const stepElement = document.createElement('div');
        stepElement.style.marginBottom = '8px';
        stepElement.style.padding = '5px';
        stepElement.style.backgroundColor = index % 2 === 0 ? '#e3f2fd' : '#f3e5f5';
        stepElement.style.borderRadius = '4px';
        stepElement.textContent = `${index + 1}. ${step}`;
        stepsContainer.appendChild(stepElement);
    });
    
    // Mostrar resultado del MST
    const mstContainer = document.getElementById('mstResult');
    mstContainer.innerHTML = '<h4>Conexiones del Árbol de Recubrimiento Mínimo:</h4>';
    mst.forEach((connection, index) => {
        const connectionElement = document.createElement('div');
        connectionElement.style.marginBottom = '5px';
        connectionElement.style.padding = '8px';
        connectionElement.style.backgroundColor = '#e8f5e8';
        connectionElement.style.borderRadius = '4px';
        connectionElement.style.border = '1px solid #27ae60';
        connectionElement.innerHTML = `<strong>${index + 1}.</strong> ${connection.from} ↔ ${connection.to} <span style="color: #27ae60; font-weight: bold;">(costo: ${connection.cost})</span>`;
        mstContainer.appendChild(connectionElement);
    });
    
    // Calcular y mostrar costo total
    const totalCost = mst.reduce((sum, connection) => sum + connection.cost, 0);
    const totalCostContainer = document.getElementById('totalCost');
    totalCostContainer.innerHTML = `
        <div style="font-size: 24px; color: #d68910;">
            💰 Costo Total: $${totalCost.toLocaleString()}
        </div>
        <div style="margin-top: 10px; font-size: 14px; color: #7f8c8d;">
            ${mst.length} conexiones para conectar ${cities.length} ciudades
        </div>
    `;
    
    // Actualizar visualización para mostrar el MST
    updateMSTVisualization(mst);
}

// Función para actualizar la visualización mostrando el MST
function updateMSTVisualization(mst) {
    const container = document.getElementById('graphVisualization');
    container.innerHTML = '';
    
    // Crear nodos (ciudades)
    cities.forEach(city => {
        const node = document.createElement('div');
        node.className = 'graph-node';
        node.style.position = 'absolute';
        node.style.left = city.x + 'px';
        node.style.top = city.y + 'px';
        node.textContent = city.name.charAt(0);
        node.title = city.name;
        container.appendChild(node);
    });
    
    // Crear solo las conexiones del MST
    mst.forEach(connection => {
        const fromCity = cities.find(c => c.name === connection.from);
        const toCity = cities.find(c => c.name === connection.to);
        
        if (fromCity && toCity) {
            const connectionElement = document.createElement('div');
            connectionElement.className = 'graph-edge mst-edge';
            connectionElement.style.position = 'absolute';
            connectionElement.style.left = (fromCity.x + toCity.x) / 2 + 'px';
            connectionElement.style.top = (fromCity.y + toCity.y) / 2 + 'px';
            connectionElement.textContent = connection.cost;
            connectionElement.title = `${connection.from} ↔ ${connection.to}: ${connection.cost}`;
            container.appendChild(connectionElement);
        }
    });
}

// Función para limpiar todos los resultados
function clearResults() {
    document.getElementById('algorithmSteps').innerHTML = '';
    document.getElementById('mstResult').innerHTML = '';
    document.getElementById('totalCost').innerHTML = '';
    
    if (cities.length > 0) {
        updateVisualization();
    }
}

// Inicialización cuando se carga la página
document.addEventListener('DOMContentLoaded', function() {
    // Generar ciudades por defecto
    generateCities();
    
    // Agregar botón para limpiar resultados
    const clearButton = document.createElement('button');
    clearButton.textContent = 'Limpiar Resultados';
    clearButton.onclick = clearResults;
    clearButton.style.background = '#e74c3c';
    clearButton.style.marginLeft = '10px';
    
    const algorithmSection = document.querySelector('.algorithm-section');
    const runButton = algorithmSection.querySelector('.primary-btn');
    runButton.parentNode.insertBefore(clearButton, runButton.nextSibling);
});

// Función para exportar resultados
function exportResults() {
    if (cities.length === 0) {
        alert('No hay resultados para exportar');
        return;
    }
    
    const { mst, steps } = primAlgorithm();
    const totalCost = mst.reduce((sum, connection) => sum + connection.cost, 0);
    
    const report = `
REPORTE DE RED DE DISTRIBUCIÓN ELÉCTRICA
=========================================

CIUDADES INVOLUCRADAS:
${cities.map(city => `- ${city.name}`).join('\n')}

ALGORITMO DE PRIM - PASOS:
${steps.map((step, index) => `${index + 1}. ${step}`).join('\n')}

ÁRBOL DE RECUBRIMIENTO MÍNIMO:
${mst.map((connection, index) => `${index + 1}. ${connection.from} ↔ ${connection.to} (costo: ${connection.cost})`).join('\n')}

COSTO TOTAL: $${totalCost.toLocaleString()}

Fecha de generación: ${new Date().toLocaleString('es-ES')}
    `;
    
    const blob = new Blob([report], { type: 'text/plain' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'reporte_red_electrica.txt';
    a.click();
    URL.revokeObjectURL(url);
}

// Agregar botón de exportar
document.addEventListener('DOMContentLoaded', function() {
    const exportButton = document.createElement('button');
    exportButton.textContent = '📄 Exportar Reporte';
    exportButton.onclick = exportResults;
    exportButton.style.background = '#9b59b6';
    exportButton.style.marginLeft = '10px';
    
    const algorithmSection = document.querySelector('.algorithm-section');
    const runButton = algorithmSection.querySelector('.primary-btn');
    runButton.parentNode.insertBefore(exportButton, runButton.nextSibling);
});
