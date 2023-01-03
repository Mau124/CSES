#include<bits/stdc++.h>
using namespace std;

const int N = 1e5+1;
#define ll long long

vector<vector<pair<ll, ll>>> adj(N);
priority_queue<pair<ll, ll>, vector<pair<ll, ll>>, greater<pair<ll, ll>>> pq;

int n, m, a, b, c;
bool visited[N];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m;
    for(int i=0; i<m; ++i) {
        cin >> a >> b >> c;
        adj[a].push_back({b, c});
        adj[b].push_back({a, c});
    }
    
    ll ans = 0;
    pq.push({0, 1}); 
    while(!pq.empty()) {
        ll cost = pq.top().first;
        ll v = pq.top().second;
        if(!visited[v]) {
            ans+=cost;
        }
        visited[v] = true;
        pq.pop();

        for(auto u: adj[v]) {
            ll cost_tmp = u.second;
            ll v_tmp = u.first;
            if(!visited[v_tmp]) {
                pq.push({cost_tmp, v_tmp});
            }
        }
    }

    bool connected = true;
    for(int i=1; i<=n; ++i) {
        if(!visited[i]) {
            connected = false;
            break;
        }
    }

    if(connected) {
        cout << ans << "\n";
    } else {
        cout << "IMPOSSIBLE\n";
    }

    return 0;
}