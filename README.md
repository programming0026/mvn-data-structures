# Estructuras de datos en Java 21

Implementación **desde cero** (sin usar `java.util`) de las estructuras de datos fundamentales, con genéricos, `Iterable` y comentarios sobre su complejidad. Java 21, sin dependencias externas.

## Qué contiene cada paquete

| Paquete | Clases | Idea |
|---------|--------|------|
| `list` | `DynamicArray<T>` | Lista con arreglo que crece al llenarse (como `ArrayList`) |
| `linkedlist` | `SinglyLinkedList<T>`, `DoublyLinkedList<T>` | Lista enlazada simple (con `reverse`) y doble (inserción y borrado O(1) en ambos extremos) |
| `stack` | `ArrayStack<T>`, `LinkedStack<T>` | Pila LIFO con arreglo y con nodos; ejemplos: paréntesis balanceados y expresiones posfijas |
| `queue` | `CircularQueue<T>`, `LinkedQueue<T>` | Cola FIFO con arreglo circular de capacidad fija y con nodos |
| `tree` | `BinarySearchTree<T>`, `GeneralTree<T>` | Árbol binario de búsqueda (insertar, buscar, borrar, altura, recorridos en orden, preorden, postorden y por niveles) y árbol general (carpetas) |

## Interfaces

Cada familia tiene una interfaz y varias implementaciones, y todas se prueban con el mismo conjunto de pruebas (principio de sustitución de Liskov):

| Interfaz | Implementaciones |
|----------|------------------|
| `Stack<T>` | `ArrayStack`, `LinkedStack` |
| `Queue<T>` | `LinkedQueue`, `CircularQueue` |
| `LinkedSequence<T>` | `SinglyLinkedList`, `DoublyLinkedList` |

## Complejidad

| Estructura | Acceso por índice | Buscar | Insertar/borrar al inicio | Insertar/borrar al final |
|------------|:-----------------:|:------:|:-------------------------:|:------------------------:|
| Lista con arreglo | O(1) | O(n) | O(n) | O(1) amortizado / O(1) |
| Lista enlazada simple | O(n) | O(n) | O(1) | O(n) |
| Lista doblemente enlazada | O(n) | O(n) | O(1) | O(1) |
| Pila (push/pop) | — | — | — | O(1) en la cima |
| Cola (enqueue/dequeue) | — | — | — | O(1) |
| BST equilibrado | — | O(log n) | — | — |
| BST degenerado (datos ordenados) | — | O(n) | — | — |

## Cuándo usar cada una

- **Lista con arreglo**: la opción por defecto; acceso rápido y buen uso de la caché del procesador.
- **Lista enlazada**: cuando insertas o borras mucho en los extremos y no necesitas acceso por índice.
- **Pila**: deshacer/rehacer, recursión manual, balanceo de símbolos, evaluación de expresiones.
- **Cola**: turnos, tareas pendientes, recorrido en anchura.
- **Árbol binario de búsqueda**: datos ordenados con búsqueda rápida. Para garantizar el equilibrio usa un árbol AVL o rojinegro (en Java, `TreeMap`/`TreeSet`).
- **Árbol general**: jerarquías (carpetas, menús, organigramas).

En proyectos reales usa las colecciones de `java.util` (`ArrayList`, `ArrayDeque`, `TreeMap`...); este proyecto sirve para entender cómo funcionan por dentro. Mira también `mvn-collections`.

## Requisitos

- JDK 21 y Maven 3.9 o superior.

## Compilar y ejecutar

```bash
mvn compile exec:java

# o como JAR ejecutable (fat jar)
mvn clean package
java -jar target/mvn-data-structures-1.0.0-SNAPSHOT-launcher.jar
```

## Pruebas

```bash
mvn test
```

## Licencia

Código de ejemplo de uso libre con fines educativos.
