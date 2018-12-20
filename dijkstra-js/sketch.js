const NODES = 10;
const DIST = 200;

const nodes = [];
const visited = [];
const unvisited = [];

class Node extends p5.Vector {
  constructor(x, y) {
    super(x, y);
    this.neighbors = [];
    this.shortestDist = 0;
    this.prevVert = null;
  }
}

// function shortestPath(nodes, start, end) {
// }

function shortestPath() {
  const visited = [];
  const unvisited = [];
  // const node = start;

  const node = nodes.filter(node => node.start)[0];
  const neighbors = node.neighbors.sort(
    (a, b) => dist(node.x, node.y, a.x, a.y) - dist(node.x, node.y, b.x, b.y)
  );
  neighbors[0];

  const path = [];
  return path;
}

function setup() {
  createCanvas(600, 400);
  background(0);

  for (let i = 0; i < NODES; i++) {
    const node = new Node(
      20 + random(width - 40),
      20 + random(height - 40),
      parseInt(random(10))
    );

    nodes.forEach(n => {
      if (dist(node.x, node.y, n.x, n.y) < DIST) {
        node.neighbors.push(n);
        if (!n.neighbors.includes(node)) n.neighbors.push(node);
      }
    });
    console.log(node.neighbors);

    nodes.push(node);
  }

  console.log(shortestPath());
}

function draw() {
  const drawn = [];
  nodes.forEach(node => {
    noStroke();
    if (node.start) fill(0, 255, 0);
    else if (node.end) fill(0, 0, 255);
    else fill(255);
    ellipse(node.x, node.y, 10, 10);
    node.neighbors.forEach(n => {
      if (drawn.includes(n)) return;
      strokeWeight(1);
      stroke(255, 180);
      line(node.x, node.y, n.x, n.y);
    });
    drawn.push(node);
  });
}
