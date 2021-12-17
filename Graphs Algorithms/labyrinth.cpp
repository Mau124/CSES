#include<bits/stdc++.h>
using namespace std;

typedef pair<int, int> pii;
#define x first
#define y second

const int h[] = {1, -1, 0, 0}, v[] = {0, 0, 1, -1};

char mat[1000][1000], par[1000][1000], ans[1000000];
int dis[1000][1000];
queue<pii> q;
pii P;
int N, M, x_ini, y_ini, x_end, y_end, i, j;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;

    for(int i=0; i<N; ++i) 
        for(int j=0; j<M; ++j) {
            cin >> mat[i][j];
            if(mat[i][j] == 'A') {
                x_ini = i; y_ini = j;
            } else if(mat[i][j] == 'B') {
                x_end = i; y_end = j;
            }
        }
  
    // Initialize BFS
    mat[x_ini][y_ini] = '#';
    q.push({x_ini, y_ini});
    while(!q.empty()) {
        P = q.front();
        q.pop();

        // Check for the adyacent vertices
        for(i=0; i<4; ++i) {
            int dx = P.x+h[i];    
            int dy = P.y+v[i];

            if(dx >= 0 && dx < N && dy >= 0 && dy < M && mat[dx][dy] != '#') {
                if(i == 0) par[dx][dy] = 'D';
                else if(i == 1) par[dx][dy] = 'U';
                else if(i == 2) par[dx][dy] = 'R';
                else if(i == 3) par[dx][dy] = 'L';
                dis[dx][dy] = dis[P.x][P.y] + 1;
                mat[dx][dy] = '#';
                q.push({dx, dy});
             }
        } 
    }

    P = {x_end, y_end};
    if(mat[P.x][P.y] == '#') {
        cout << "YES " << dis[P.x][P.y] << "\n";
        
        for(int i = dis[P.x][P.y]; i > 0; i--) {
            ans[i] = par[P.x][P.y];
            if(ans[i] == 'U') P = {P.x+1, P.y};
            else if(ans[i] == 'R') P = {P.x, P.y-1};
            else if(ans[i] == 'D') P = {P.x-1, P.y};
            else if(ans[i] == 'L') P = {P.x, P.y+1};
        }

        for(int i=1; i<=dis[x_end][y_end]; ++i) 
            cout << ans[i];
        cout << "\n";
    } else {
        cout << "NO\n";
    }
    return 0;
}
