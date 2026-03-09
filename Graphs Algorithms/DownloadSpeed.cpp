#include <vector>
#include <iostream>
#include <climits>
#include <queue>

using namespace std;

#define INF LONG_MAX
#define ll long long

int n, m;
vector<vector<ll>> capacity(501, vector<ll>(501));
vector<vector<int>> adj(501);

ll min(ll a, ll b) {
    if(a < b) {
        return a;
    }
    return b;
}

bool reachable(int source, int sink, vector<int>& parent) {
    queue<int> q;
    q.push(source);
    while (!q.empty()) {
        int node = q.front();
        q.pop();
        for (int son : adj[node]) {
            ll w = capacity[node][son];
            if (w <= 0 || parent[son] != -1) continue;
            parent[son] = node;
            q.push(son);
        }
    }
    return parent[sink] != -1;
}

ll maxflow(int source, int sink) {
	int n = adj.size();
	vector<int> parent(n, -1);

	ll flow = 0;
	
	while (reachable(source, sink, parent)) {
		int node = sink;
		ll curr_flow = INF;
		while (node != source) {
			curr_flow = min(curr_flow, capacity[parent[node]][node]);
			node = parent[node];
		}
		node = sink;
		while (node != source) {
			capacity[parent[node]][node] -= curr_flow;
			capacity[node][parent[node]] += curr_flow;
			node = parent[node];
		}
		flow += curr_flow;
		fill(parent.begin(), parent.end(), -1);
	}

	return flow;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;

    for(int i=0; i<m; ++i) {
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].push_back(b);
        adj[b].push_back(a);
        capacity[a][b] += c;
    }

    cout << maxflow(1, n);

    return 0;
}