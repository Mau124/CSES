#include<bits/stdc++.h>
using namespace std;
 
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
 
    string s;
    cin >> s;
 
    char pivot = s[0];
    int times = 1, ans = 0;
 
    for(int i=1; i<s.size(); ++i) {
        if (s[i] == pivot) times++;
        else {
            pivot = s[i];
            ans = max(ans, times);
            times = 1;
        }
    }
    ans = max(ans, times);
    cout << ans << "\n";
    return 0;
}