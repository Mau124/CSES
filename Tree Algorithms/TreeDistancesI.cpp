#include<bits/stdc++.h>
using namespace std;

const int MAX = 2e5+2;

int inside[MAX], insideExclude[MAX], ans[MAX];
vector<bool> visited(MAX);
vector<vector<int>> adj(MAX);

void insideDfs(int v) {
    visited[v] = true;
    int best = 0, secondBest = 0;
    for(int u: adj[v]) {
        if(!visited[u]) {
            insideDfs(u);
            if(inside[u]+1 >= best) {
                secondBest = best;
                best = inside[u]+1;
            } else if(inside[u]+1 > secondBest) {
                secondBest = inside[u]+1;
            }
        }
    }
    inside[v] = best;
    insideExclude[v] = secondBest;
}

void outsideDfs(int v, int outside) {
    visited[v] = true;
    ans[v] = max(inside[v], outside);
    for(int u: adj[v]) {
        if(!visited[u]) {
            if(inside[u] + 1 == inside[v]) {
                outsideDfs(u, max(outside+1, insideExclude[v]+1));
            } else {
                outsideDfs(u, ans[v]+1);
            }
        }
    }
}

int main() {
    int n;
    cin >> n;
    for(int i=0; i<n-1; ++i) {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    // cout << "Entering dfs" << endl;
    insideDfs(1);
    std::fill(visited.begin(), visited.end(), false);
    outsideDfs(1, 0);
    // cout << "Entering upperDfs" << endl;


    // cout << "Printing maxValue:: " << maxVal.first << " with index:: " << maxVal.second << endl;
    // cout << "Printing secondMaxValue:: " << secondMaxVal.first << " with node:: " << secondMaxVal.second << endl;

    // cout << "Printing inside" << endl;
    // for(int i=1; i<=n; ++i) {
    //     cout << inside[i] << " ";
    // }
    // cout << "\nPrinting inside Exclude" << endl;
    // for(int i=1; i<=n; ++i) {
    //     cout << insideExclude[i] << " ";
    // }
    // cout << "\nPrinting answer" << endl;
    for(int i=1; i<=n; ++i) {
        cout << ans[i] << " ";
    }

    return 0;
}