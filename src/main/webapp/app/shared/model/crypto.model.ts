import { IWatchList } from 'app/shared/model/watch-list.model';

export interface ICrypto {
  id?: string;
  symbol?: string | null;
  name?: string;
  currentPrice?: number | null;
  marketCap?: number | null;
  marketCapRank?: number | null;
  totalVolume?: number | null;
  high24?: number | null;
  low24?: number | null;
  priceChange24?: number | null;
  priceChangePercentage24hr?: number | null;
  totalSupply?: number | null;
  ath?: number | null;
  athDate?: string | null;
  atl?: number | null;
  atlDate?: string | null;
  watchList?: IWatchList | null;
}

export const defaultValue: Readonly<ICrypto> = {};
