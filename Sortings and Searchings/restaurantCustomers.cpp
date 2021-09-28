#include<bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n, ans = INT_MIN, sum = 0;
    cin >> n;
    vector<pair<int, int>> v;

    for(int i=0; i<n; ++i) {
        int a, b;
        cin >> a >> b;
        v.push_back(make_pair(a, 1));
        v.push_back(make_pair(b, -1));
    }

    sort(v.begin(), v.end());

    for(int i=0; i<2*n; ++i) {
        sum += v[i].second;

        ans = max(ans, sum);
    }
    cout << ans << "\n";
    return 0;
}
