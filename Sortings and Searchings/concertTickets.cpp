#include<bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, index, aux;
    cin >> n >> m;
    map<int, int> h;
    vector<int> t(m);

    for(int i=0; i<n; ++i) {
        cin >> aux;
        h[aux]++;
    }
    for(int i=0; i<m; ++i) cin >> t[i];

    for(int i=0; i<m; ++i) {
        map<int, int>::iterator up = h.upper_bound(t[i]);

        if(up != h.begin()) {
            up = prev(up);

            cout << up->first << "\n";
            up->second--;

            if(up->second <= 0) 
                h.erase(up);
        } else {
            cout << "-1\n";
        }
    }

    return 0;
}
