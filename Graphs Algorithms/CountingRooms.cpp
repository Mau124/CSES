#include<bits/stdc++.h>
using namespace std;

int rooms = 0;
int n, m;
char adj[1001][1001];
bool visited[1001][1001];

void dfs(int i, int j) {
    if(visited[i][j] || i < 1 || i > n || j < 1 || j > m)
        return;

    visited[i][j] = true;
    if(adj[i][j] == '#')
        return;
   
    dfs(i-1, j);
    dfs(i, j+1);
    dfs(i+1, j);
    dfs(i, j-1);
}

int main() {
    cin >> n >> m;

    for(int i=1; i<=n; ++i) 
        for(int j=1; j<=m; ++j) 
            cin >> adj[i][j];
    
    for(int i=1; i<=n; ++i) {
        for(int j=1; j<=m; ++j) {
            if(adj[i][j] == '.' && !visited[i][j])  {
                rooms++;
                dfs(i, j);
            } else {
                visited[i][j] = true;
            }
        } 
    } 

    cout << rooms << "\n";

    return 0;
}
