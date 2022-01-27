#include<bits/stdc++.h>
using namespace std;

#define N 501
#define ll long long
#define INF 1e15

ll dis[N][N];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    // Fill matrix with inf excep the symmetrical ones
    for(int i=1; i<N; ++i) {
        for(int j=1; j<N; ++j) {
            if(i == j) 
                dis[i][j] = 0;  
            else
                dis[i][j] = INF;
        }
    }

    int n, m, q;
    cin >> n >> m >> q;

    for(int i=0; i<m; ++i) {
        int a, b, c;
        cin >> a >> b >> c;

        if(c < dis[a][b]) {
            dis[a][b] = c;
            dis[b][a] = c;
        }
    }

    // Make floyd warshall algorithm
    for(int k=1; k<=n; ++k) {
        // Trasverse the matrix
        for(int i=1; i<=n; ++i) {
            for(int j=1; j<=n; ++j) {
                dis[i][j] = min(dis[i][j], dis[i][k] + dis[k][j]);       
            }
        }
    }

    // Answer queries
    for(int i=0; i<q; ++i) {
        int a, b;
        cin >> a >> b;

        if(dis[a][b] == INF) 
            cout << "-1\n";
        else
            cout << dis[a][b] << "\n";
    }

    // cout << LONG_LONG_MAX << endl;
    return 0;
}
