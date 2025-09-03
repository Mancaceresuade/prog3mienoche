# ⚡ Diseño de Red de Distribución Eléctrica

## Descripción

Esta aplicación web implementa el **Algoritmo de Prim** para diseñar una red de distribución eléctrica eficiente que conecte múltiples estaciones eléctricas en diferentes ciudades.

## Características Principales

- **Representación del Grafo**: Utiliza una lista de adyacencia para representar las conexiones entre ciudades
- **Algoritmo de Prim**: Implementa el algoritmo completo para encontrar el Árbol de Recubrimiento Mínimo (MST)
- **Visualización Interactiva**: Muestra el grafo y las conexiones resultantes de forma visual
- **Cálculo de Costos**: Considera distancia y factores de terreno para calcular costos realistas
- **Interfaz Moderna**: Diseño responsivo y atractivo con animaciones

## Funcionalidades

### 1. Configuración de la Red
- Configurar el número de ciudades (3-10)
- Personalizar nombres de ciudades
- Generar posiciones aleatorias para las estaciones

### 2. Representación del Grafo
- **Lista de Adyacencia**: Muestra todas las conexiones posibles entre ciudades
- **Visualización Gráfica**: Representa nodos (ciudades) y aristas (conexiones) visualmente
- **Matriz de Costos**: Calcula costos basados en distancia y factor de terreno

### 3. Algoritmo de Prim
- **Paso a Paso**: Muestra cada iteración del algoritmo
- **Selección de Aristas**: Identifica la conexión de menor costo en cada paso
- **Conectividad**: Asegura que todas las ciudades estén conectadas

### 4. Resultados
- **Árbol de Recubrimiento Mínimo**: Lista de conexiones óptimas
- **Costo Total**: Suma de todos los costos de conexión
- **Visualización del MST**: Muestra solo las conexiones seleccionadas

## Cómo Usar la Aplicación

### Paso 1: Configurar la Red
1. Ingresa el número de ciudades (entre 3 y 10)
2. Escribe los nombres de las ciudades separados por comas
3. Haz clic en "Generar Ciudades"

### Paso 2: Analizar el Grafo
- Revisa la representación visual del grafo
- Examina la lista de adyacencia con todos los costos

### Paso 3: Ejecutar el Algoritmo
1. Haz clic en "Ejecutar Algoritmo de Prim"
2. Observa los pasos del algoritmo en tiempo real
3. Revisa las conexiones seleccionadas

### Paso 4: Interpretar Resultados
- El MST muestra las conexiones óptimas
- El costo total representa la inversión mínima necesaria
- La visualización actualizada muestra solo las conexiones del MST

## Características Técnicas

### Implementación del Algoritmo de Prim
```javascript
// Pseudocódigo del algoritmo implementado:
1. Inicializar conjunto de nodos visitados con el primer nodo
2. Mientras no todos los nodos estén visitados:
   a. Encontrar la arista de menor costo que conecte un nodo visitado con uno no visitado
   b. Agregar la arista al MST
   c. Marcar ambos nodos como visitados
3. Retornar el MST resultante
```

### Estructura de Datos
- **Lista de Adyacencia**: `graph[ciudad] = [{to: ciudad_destino, cost: costo}, ...]`
- **Matriz de Adyacencia**: `adjacencyMatrix[i][j] = costo_entre_ciudad_i_y_j`
- **Clases**: `City` (nombre, coordenadas) y `Connection` (origen, destino, costo)

### Cálculo de Costos
```
Costo = Distancia_Euclidiana × Factor_Terreno
Factor_Terreno = 1.0 + random(0, 1.5)
```

## Archivos del Proyecto

- `index.html` - Estructura HTML de la página
- `styles.css` - Estilos CSS para el diseño visual
- `script.js` - Lógica JavaScript y algoritmo de Prim
- `README.md` - Este archivo de documentación

## Tecnologías Utilizadas

- **HTML5**: Estructura semántica de la página
- **CSS3**: Estilos modernos con gradientes, sombras y animaciones
- **JavaScript ES6+**: Lógica de programación y algoritmos
- **Algoritmos de Grafos**: Implementación del algoritmo de Prim

## Casos de Uso

### Aplicaciones Reales
- **Planificación Urbana**: Diseño de redes de transporte público
- **Telecomunicaciones**: Conexión de nodos de red
- **Logística**: Optimización de rutas de distribución
- **Infraestructura**: Diseño de redes de agua, gas o electricidad

### Beneficios del Algoritmo de Prim
- **Eficiencia**: Conecta todas las ciudades con el costo mínimo total
- **Conectividad**: Garantiza que no haya ciudades aisladas
- **Optimización**: Encuentra la solución óptima para el problema

## Personalización

### Modificar Factores de Costo
```javascript
// En la función generateGraph(), puedes modificar:
const terrainFactor = 1.0 + Math.random() * 1.5; // Factor de terreno
const cost = Math.round(distance * terrainFactor); // Cálculo de costo
```

### Agregar Nuevas Métricas
- Factores de población
- Restricciones geográficas
- Costos de mantenimiento
- Capacidad de transmisión

## Contribuciones

Esta aplicación es un proyecto educativo que demuestra la implementación práctica del algoritmo de Prim. Las mejoras y sugerencias son bienvenidas.

## Licencia

Proyecto educativo para fines de aprendizaje y demostración de algoritmos de grafos.
