#include<bits/stdc++.h>
using namespace std;

#define N 200001

vector<int> adj[N];
int n, i, a, b;

pair<int, int> treeDiameter(int root, vector<int> adj[]) {
    bool visited[N] = {false};
    int dis[N] = {0};
    queue<int> q;
    int v;

    q.push(root);
    visited[root] = true;

    while(!q.empty()) {
        v = q.front();
        q.pop();

        for(int u: adj[v]) {
            if(!visited[u]) {
                visited[u] = true;
                dis[u] = dis[v] + 1;
                q.push(u);
            }
        }
    }

    return {v, dis[v]};
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n;
    for(i=0; i<n-1; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    // Look for an endpoint
    pair<int, int> first_end_point = treeDiameter(1, adj);
    //cout << "Primer endpoint" << endl;
    //cout << first_end_point.first << " " << first_end_point.second << endl;

    // v is and endpoint 
    // now we have to do a bfs using v
    pair<int, int> second_end_point = treeDiameter(first_end_point.first, adj);
    //cout << "Segundo endpoint" << endl;
    //cout << second_end_point.first << " " << second_end_point.second << endl;

    cout << second_end_point.second << "\n";

    return 0;
}

