#include<bits/stdc++.h>
using namespace std;

const int N = 1e5+1;

int n, m, a, b, components, maxSize;
int parent[N], size[N];

int find(int node) {
    if(node == parent[node])
        return node;
    return parent[node] = find(parent[node]);
}

void connect(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);
    if(rootA != rootB) {
        if(size[rootA] < size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
            maxSize = max(maxSize, size[rootB]);
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            maxSize = max(maxSize, size[rootA]);
        }
        components--;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m;
    // Initialize trees
    components = n;
    for(int i=0; i<=n; ++i) {
        parent[i] = i;
        size[i] = 1;
    }

    // Answer the queries
    for(int q=0; q<m; ++q) {
        cin >> a >> b;
        connect(a, b);
        cout << components << " " << maxSize << "\n";
    }

    return 0;
}