#include<bits/stdc++.h>
using namespace std;

#define N 100001
#define ll long long

vector<vector<pair<ll, ll>>> adj(N);
vector<ll> dis(N, LLONG_MAX);
bool processed[N];
priority_queue<pair<ll, ll>, vector<pair<ll, ll>>, greater<pair<ll, ll>>> pq;

ll n, m, i, j, a, b, c;

int main() {
    ios_base::sync_with_stdio(false);
	cin.tie(NULL);

    cin >> n >> m;

    for(i=0; i<m; ++i) {
        cin >> a >> b >> c;
        adj[a].push_back({b, c});
    }

    dis[1] = 0;
    pq.push({0, 1});
    
    while(!pq.empty()) {
        ll d = pq.top().first;
        ll v = pq.top().second;
        pq.pop();

        if(d > dis[v]) 
            continue;

        for(auto u: adj[v]) {
            ll d_temp = u.second;
            ll v_temp = u.first;

            if(d + d_temp < dis[v_temp]) {
                dis[v_temp] = d + d_temp;
                pq.push({dis[v_temp], v_temp});
            }
        } 
    }

    for(i=1; i<=n; ++i)
        cout << dis[i] << " ";
    cout << "\n";

    return 0;
}
