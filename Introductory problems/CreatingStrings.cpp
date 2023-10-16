#include<bits/stdc++.h>
using namespace std;

int main() {
    string s;
    cin >> s;
    
    sort(s.begin(), s.end());
    
    int i=0;
    do {
        i++;
    } while(next_permutation(s.begin(), s.end()));
    cout << i << endl;
    int it = 0;
    do {
        cout << "[" << it << "]" << " " << s << endl;
        it++;
    } while(next_permutation(s.begin(), s.end()));

    return 0;
}