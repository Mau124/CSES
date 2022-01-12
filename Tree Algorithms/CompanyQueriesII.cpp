// Problem that asks for the LCA
// It requires RMQ, in this case sparse tables to answer queries quickly
#include<bits/stdc++.h>
using namespace std;

// Number of nodes
#define N 200001
#define LOG 18

vector<int> adj[N];
int depth[2][N], pos[N], it = 0;

// sp if for sparse_stable
int sp[2*N][LOG];
int idx[2*N][LOG];

void dfs(int v, int parent, int aux_depth) {
    depth[0][it] = v;
    depth[1][it] = aux_depth;
    pos[v] = it;
    it++;

    for(int u: adj[v]) {
        if(u != parent) {
            dfs(u, v, aux_depth+1);
            depth[0][it] = v;
            depth[1][it] = aux_depth;
            it++;
       } 
    }
}

void print(int a[], int n) {
    for(int i=0; i<n; ++i)
        cout << a[i] << " ";
    cout << "\n";
}

// Return minimun in range of sparse table
int query(int l, int r) {
    // Get size of range
    if(l > r) {
        int tmp = l;
        l = r;
        r = tmp;
    }

    int diff = r-l+1;
    int k = 0;

    //cout << "Imprimiendo los valores de l y r\n";
    //cout << l << " " << r << endl;
    // Closest power of two to diff
    while((1<<(k+1)) <= diff) 
        k++;

    if(sp[l][k] < sp[r-(1<<k)+1][k]) {
        return idx[l][k];
    } else {
        return idx[r-(1<<k)+1][k];
    }
    //return min(sp[l][k], sp[r-(1<<k)+1][k]);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, q, boss, a, b;
    cin >> n >> q;

    for(int i=2; i<=n; ++i) {
         cin >> boss;
         adj[i].push_back(boss);
         adj[boss].push_back(i);
    } 

    // Euler tour
    it = 0;
    dfs(1, -1, 1);

    //print(depth[0], 2*n-1);
    //print(depth[1], 2*n-1);
    //print(pos, n+1);

    // Construct sparse table
    for(int i = 0; i<2*n-1; ++i) {
        idx[i][0] = depth[0][i];
        sp[i][0] = depth[1][i];
    }

    for(int j = 1; j < LOG; ++j) {
        for(int i=0; i < (2*n-1)-(1<<j)+1; ++i) {

            if(sp[i][j-1] < sp[i+(1<<(j-1))][j-1]) {
                idx[i][j] = idx[i][j-1];
                sp[i][j] = sp[i][j-1];
            } else {
                idx[i][j] = idx[i+(1<<(j-1))][j-1];
                sp[i][j] = sp[i+(1<<(j-1))][j-1];
            }
            // sp[i][j] = min(sp[i][j-1], sp[i+(1<<(j-1))][j-1]);
        } 
    }

    //cout << "Imprimiendo la sparse table\n";
    //for(int i=0; i<2*n; ++i) {
        //for(int j=0; j < 2*n-1; ++j) {
            //cout << sp[i][j] << " ";
        //}
        //cout << "\n";
    //}

    // Answer queries
    for(int qq=0; qq<q; ++qq) {
        cin >> a >> b;
        // cout << "1\n";
        cout << query(pos[a], pos[b]) << "\n";
    }

    return 0;
}
