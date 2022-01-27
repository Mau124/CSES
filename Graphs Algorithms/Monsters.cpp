#include<bits/stdc++.h>
using namespace std;

#define N 1000
#define endl "\n"

char mat[N][N], par[N][N];
int n, m, row_end, col_end, dis[N][N];

const int v[] = {-1, 0, 1, 0};
const int h[] = {0, 1, 0, -1};

queue<pair<int, int>> player;
queue<pair<int, int>> monsters;
bool isPosible = false;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m;

    for(int i=0; i<n; ++i) 
    {
        for(int j=0; j<m; ++j) 
        {
            cin >> mat[i][j];

            if(mat[i][j] == 'A') {
                player.push({i, j});
                mat[i][j] = 'A';
            }

            if(mat[i][j] == 'M') {
                pair<int, int> tmp = make_pair(i, j);
                monsters.push(tmp);
                mat[i][j] = '#';
            }
        }
    }

    // Start bfs
    int possibilities;
    while(!player.empty() && !isPosible) 
    {
        // Hacer bfs sobre los monstruos
        possibilities = monsters.size();

        for(int k=0; k<possibilities; ++k) {
            pair<int, int> tmp = monsters.front();
            monsters.pop();

            // Check for adyacent vertices
            for(int i=0; i<4; ++i) {
                int dv = tmp.first+v[i];
                int dh = tmp.second+h[i];

                if(dv >= 0 && dv < n && dh >= 0 && dh < m && mat[dv][dh] != '#') {
                    monsters.push({dv, dh}); 
                    mat[dv][dh] = '#';
                }
            }
        }

        // Hacer bfs sobre el jugador
        possibilities = player.size();
        for(int k=0; k<possibilities; ++k) 
        {
            pair<int, int> tmp = player.front();
            player.pop();

            if(tmp.first == 0 || tmp.first == n-1 || tmp.second == 0 || tmp.second == m-1) 
            {
                isPosible = true;
                break;
            }

            for(int i=0; i<4; ++i) 
            {
                int dv = tmp.first+v[i];
                int dh = tmp.second+h[i];

                if(dv >= 0 && dv < n && dh >= 0 && dh < m && mat[dv][dh] != '#' && mat[dv][dh] != 'A') 
                {
                    if(dv == 0 || dh == 0 || dv == n-1 || dh == m-1) 
                    {
                        isPosible = true;
                        row_end = dv;
                        col_end = dh; 
                    }

                    // Checar como se llego ahi
                    if(i == 0) par[dv][dh] = 'U';
                    if(i == 1) par[dv][dh] = 'R';
                    if(i == 2) par[dv][dh] = 'D';
                    if(i == 3) par[dv][dh] = 'L';
                    player.push({dv, dh});
                    dis[dv][dh] = dis[tmp.first][tmp.second]+1;
                    mat[dv][dh] = 'A';
                }

            }
        }

        // cout << "Second: " << second << endl;
        // print(n, m);
        // cout << endl;
        // second++;
    }

    if(isPosible) {
        int distance = dis[row_end][col_end];
        cout << "YES" << endl;;
        cout << distance << endl;

        int row = row_end, col = col_end;
        vector<char> ans;
        for(int i=0; i<distance; ++i) {
            ans.push_back(par[row][col]);
            if(par[row][col] == 'U') row++;
            else if(par[row][col] == 'R') col--;
            else if(par[row][col] == 'D') row--;
            else if(par[row][col] == 'L') col++;
        }

        for(int i=distance-1; i>=0; --i) {
            cout << ans[i];
        }
        cout << endl;
    } else {
        cout << "NO" << endl;
    }

    return 0;
}
