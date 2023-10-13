#include<bits/stdc++.h>
using namespace std;

const int MAX = 5000;
int numbers[MAX];
long long dp[2][MAX][MAX];

int main() {
    int n;
    cin >> n;

    for(int i=0; i<n; ++i) {
        cin >> numbers[i];
    }

    int lambda = 0;
    for(int d=n; d>0; --d) {
        for(int i=0; i<d; ++i) {
            int j = i+lambda;

            if(i == j) {
                // Player 0 turn
                dp[0][i][j] = numbers[i];

                // We don't need to initialize player 1 since it is already 0
            } else {
                // Player 0 turn
                dp[0][i][j] = max(numbers[i]+dp[1][i+1][j], numbers[j]+dp[1][i][j-1]);

                // Player 1 turn
                dp[1][i][j] = min(dp[0][i+1][j], dp[0][i][j-1]);
            }

        }
        lambda++;
    }

    // cout << "Printing matrices" << endl;
    // cout << "Player 0 " << endl;
    // for(int i=0; i<n; ++i) {
    //     for(int j=0; j<n; ++j) {
    //         cout << dp[0][i][j] << " ";
    //     }
    //     cout << "\n";   
    // }

    // cout << endl;
    // cout << "Player 1 " << endl;
    // for(int i=0; i<n; ++i) {
    //     for(int j=0; j<n; ++j) {
    //         cout << dp[1][i][j] << " ";
    //     }
    //     cout << "\n";   
    // }

    cout << dp[0][0][n-1] << endl;

    return 0;
}