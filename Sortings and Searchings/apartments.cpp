#include<bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, k;
    cin >> n >> m >> k;
    vector<int> d(n), s(m);

    for(int i=0; i<n; ++i) cin >> d[i];
    for(int i=0; i<m; ++i) cin >> s[i];

    sort(d.begin(), d.end());
    sort(s.begin(), s.end());

    int ptr1 = 0, ptr2 = 0, ans = 0;
    while(ptr1 < s.size() && ptr2 < d.size()) {
        if(d[ptr2] < s[ptr1]-k) {
            ptr2++;
        }
        else if(d[ptr2] >= s[ptr1]-k && d[ptr2] <= s[ptr1]+k) {
            ptr1++;
            ptr2++;
            ans++;
        } else {
            ptr1++;
        }
    }

    cout << ans << "\n";
    return 0;
}
