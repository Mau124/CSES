// Get LCA
// Use sparse tables to answer queries

#include<bits/stdc++.h>
using namespace std;

#define N 200001
#define LOG 19

vector<int> adj[N];
int depth[2*N], pos[N], it;

// sp is for sparse_table
int sp[LOG][2*N];

void dfs(int v, int par, int l) {
    depth[it] = l;
    pos[v] = it;
    it++; 

    for(int u: adj[v]) {
        if(u != par) {
            dfs(u, v, l+1);
            depth[it] = l;
            it++;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    // Read number of nodes and number of queries
    int n, q, a, b;
    cin >> n >> q;

    // Read edges between nodes
    for(int i=0; i<n-1; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    // Euler tour for LCA
    dfs(1, -1, 1); 

    // First row of sparse table
    for(int i=0; i<2*n-1; ++i) {
        sp[0][i] = depth[i];
    }

    // Construct sparse table
    for(int i=1; i<LOG; ++i) {
        for(int j=0; j<((2*n-1) - (1<<j) + 1); ++j) {
            sp[][
        } 
    }      

    // Read queries
    for(int qq=0; qq<q; ++qq) {
        cin >> a >> b;


    } 

    return 0;
}
