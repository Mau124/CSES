#include <vector>
#include <iostream>
#include <climits>
#include <queue>
#include <set>

using namespace std;

#define INF LONG_MAX
#define ll long long

const int N = 2e5+1;

int n, m, it;
vector<int> degree(N);
vector<multiset<int>> adj(N);
vector<int> circuit(N);

ll min(ll a, ll b) {
    if(a < b) {
        return a;
    }
    return b;
}

void eulerCircuit(int v) {
    while(!adj[v].empty()) {
        int u = *adj[v].begin();
        adj[v].erase(u);
        adj[u].erase(v);
        eulerCircuit(u);
        circuit[it++] = v;
    }
    return;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    it = 0;
    cin >> n >> m;

    for(int i=0; i<m; ++i) {
        int a, b;
        cin >> a >> b;
        adj[a].insert(b);
        adj[b].insert(a);
        degree[a]++;
        degree[b]++;
    }

    for(int i=1; i<=n; ++i) {
        // cout << "Node: " << i << ", degree: " << degree[i] << endl;
        if(degree[i] % 2 != 0) {
            cout << "IMPOSSIBLE";
            return 0;
        }
    }

    circuit[it++] = 1;
    eulerCircuit(1);

    for(int i=1; i<=n; ++i) {
        if(!adj[i].empty()) {
            cout << "IMPOSSIBLE";
            return 0;
        }
    }

    for(int i=it-1; i>=0; --i) {
        cout << circuit[i] << " ";
    }

    return 0;
}