#include<bits/stdc++.h>
using namespace std;

void print(pair<int, int> p) {
    cout << p.first << " " << p.second << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;
    vector<pair<int, int>> movies(n);
    stack<pair<int, int>> s;

    for(int i=0; i<n; ++i) {
        cin >> movies[i].first >> movies[i].second;
    }

    sort(movies.begin(), movies.end());

    s.push(movies[0]);
    for(int i=1; i<n; ++i) {
        if(movies[i].first >= s.top().second) {
            s.push(movies[i]); 
        } else {
            if(movies[i].second < s.top().second) {
                s.pop();
                s.push(movies[i]);
            }
        }
    } 

    cout << s.size() << "\n";
}
