import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import React, { useState } from 'react';

function createData(marketCapRank, name, price, percentChange, volume, marketCap, symbol) {
  return {
    marketCapRank,
    name,
    price,
    percentChange,
    volume,
    marketCap,
    symbol,
  };
}

export default function CryptoTable({ cryptos }) {
  const [rowsToDisplay, setRowsToDisplay] = useState(10);

  const rows = cryptos.map(crypto => {
    return createData(
      crypto.market_cap_rank,
      crypto.name,
      crypto.current_price,
      crypto.price_change_percentage_24h,
      crypto.total_volume,
      crypto.market_cap,
      crypto.symbol
    );
  });
  //console.log(cryptos);
  return (
    <TableContainer component={Paper}>
      <Table aria-label="price-table">
        <TableHead>
          <TableRow>
            <TableCell align="right">Market Cap Rank</TableCell>
            <TableCell align="right">Name</TableCell>
            <TableCell align="right">Price(USD)</TableCell>
            <TableCell align="right">Percent Change</TableCell>
            <TableCell align="right">Volume(USD)</TableCell>
            <TableCell align="right">Market Cap(USD)</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.slice(rowsToDisplay).map(row => (
            <TableRow key={row.marketCapRank}>
              <TableCell align="right">{row.marketCapRank}</TableCell>
              <TableCell align="right">
                {row.name} ({row.symbol?.toUpperCase()})
              </TableCell>
              <TableCell align="right">${row.price?.toLocaleString(undefined, { minimumFractionDigits: 2 })}</TableCell>
              <TableCell align="right">{row.percentChange}%</TableCell>
              <TableCell align="right">${row.volume?.toLocaleString(undefined, { minimumFractionDigits: 2 })}</TableCell>
              <TableCell align="right">${row.marketCap?.toLocaleString(undefined, { minimumFractionDigits: 2 })}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
