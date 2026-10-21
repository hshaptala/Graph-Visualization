# Graph-Visualization

Graph-Visualization is a Java 11 desktop application for loading and visualizing graph datasets from CSV files.  
It uses a Swing UI (Nimbus look and feel), an in-repo graph model, and graph-processing features such as minimum spanning tree generation and edge bundling.

## Features

- Load predefined CSV datasets from the application window.
- Display nodes and edges on a drawing canvas.
- Toggle between:
  - the original graph,
  - a minimum spanning tree (Prim-based),
  - and an edge-bundled view.
- Adjust rendering interactively:
  - node size,
  - edge transparency,
  - smoothed vs. polyline edge drawing.

## Project structure

- Application entry point: [`src/main/java/info/iut/sae2/Main.java`](src/main/java/info/iut/sae2/Main.java)
- UI:
  - [`src/main/java/info/iut/sae2/viewer/GraphViewer.java`](src/main/java/info/iut/sae2/viewer/GraphViewer.java)
  - [`src/main/java/info/iut/sae2/viewer/GraphCanvas.java`](src/main/java/info/iut/sae2/viewer/GraphCanvas.java)
  - [`src/main/java/info/iut/sae2/viewer/NodeView.java`](src/main/java/info/iut/sae2/viewer/NodeView.java)
  - [`src/main/java/info/iut/sae2/viewer/EdgeView.java`](src/main/java/info/iut/sae2/viewer/EdgeView.java)
- Graph model and loading:
  - [`src/main/java/info/iut/sae2/graphs/Graph.java`](src/main/java/info/iut/sae2/graphs/Graph.java)
  - [`src/main/java/info/iut/sae2/graphs/GraphLoader.java`](src/main/java/info/iut/sae2/graphs/GraphLoader.java)
  - [`src/main/java/info/iut/sae2/graphs/IGraph.java`](src/main/java/info/iut/sae2/graphs/IGraph.java)
- Algorithms:
  - [`src/main/java/info/iut/sae2/algorithms/PrimAlgo.java`](src/main/java/info/iut/sae2/algorithms/PrimAlgo.java)
  - [`src/main/java/info/iut/sae2/algorithms/EdgeBuilding.java`](src/main/java/info/iut/sae2/algorithms/EdgeBuilding.java)
- NetBeans/Ant project files:
  - [`build.xml`](build.xml)
  - [`nbproject/project.properties`](nbproject/project.properties)
  - [`nbproject/project.xml`](nbproject/project.xml)
- Tests:
  - [`test/main/java/info/iut/sae2/graphs/GraphTest.java`](test/main/java/info/iut/sae2/graphs/GraphTest.java)
  - [`test/main/java/info/iut/sae2/graphs/GraphBundleTests.java`](test/main/java/info/iut/sae2/graphs/GraphBundleTests.java)

## Prerequisites

- JDK 11 (project source/target is set to 11 in `nbproject/project.properties`)
- Apache Ant (targets are defined via `build.xml` / `nbproject/build-impl.xml`)
- NetBeans (optional, but this is a NetBeans Java SE project)

## Setup and quick start

From the repository root:

```bash
ant clean
ant compile
ant run
```

`ant -p` lists available targets, including `clean`, `compile`, `jar`, `run`, and `test`.

You can also build the distributable JAR:

```bash
ant jar
```

The configured output JAR path is:

`dist/VisuGraph_Shaptala_Retail.jar`

The configured main class is:

`main.java.info.iut.sae2.Main`

## Dataset format and usage

The app loads graphs from two CSV files:

- `*_nodes.csv` for node IDs and coordinates
- `*_edges.csv` for edge endpoints

Format expected by `GraphLoader`:

- Node row: `nodeId;x y`
- Edge row: `sourceId;targetId`
- Separator: semicolon (`;`)
- Coordinates separator: space (` `)
- Encoding: US-ASCII
- No header rows

Example node file content:

```text
0;13.0 7.5
1;5.5 8.5
```

Example edge file content:

```text
0;1
1;2
```

Several datasets are already included in the repository root (for example `example_*`, `hollywood_*`, `us_migrations_*`, `ubc2K_*`, `air_traffic_*`).

## Architecture overview

- `Main` sets Nimbus look and feel and opens the main window.
- `GraphViewer` builds the Swing controls and handles dataset loading and view mode switching.
- `GraphCanvas` transforms graph coordinates to viewport coordinates and paints nodes/edges.
- `Graph` stores nodes/edges and provides operations from `IGraph`.
- `GraphLoader` builds a `Graph` from node/edge CSV files.
- `PrimAlgo` computes a minimum spanning tree using Euclidean distances between node coordinates.
- `EdgeBuilding` finds paths in the MST (BFS) to produce bend points used by graph edge bundling.

## Development workflow

Useful Ant commands:

```bash
ant clean
ant compile
ant test
ant run
ant jar
```

Notes from repository verification:

- `ant clean` and `ant compile` run successfully.
- `ant test` currently fails at test compilation in the current repository state (assertion symbol resolution errors in `GraphTest.java`/`GraphBundleTests.java`).

## Contributing

No dedicated contributing guide (`CONTRIBUTING.md`) is currently present in this repository.

## Security

No security policy file (`SECURITY.md`) is currently present in this repository.

## License

No license file is currently present in this repository.
